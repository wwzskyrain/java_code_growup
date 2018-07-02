package concurrency;//: concurrency/CarBuilder.java
// A complex example of tasks working together.

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

class Car {     //
    private final int id;
    private boolean
            engine = false, driveTrain = false, wheels = false;

    public Car(int idn) {
        id = idn;
    }

    // Empty Car object:
    public Car() {
        id = -1;
    }

    public synchronized int getId() {
        return id;
    }

    public synchronized void addEngine() {
        engine = true;
    }

    public synchronized void addDriveTrain() {
        driveTrain = true;
    }

    public synchronized void addWheels() {
        wheels = true;
    }

    public synchronized String toString() {
        return "Car " + id + " [" + " engine: " + engine
                + " driveTrain: " + driveTrain
                + " wheels: " + wheels + " ]";
    }
}

class CarQueue extends LinkedBlockingQueue<Car> {
}

class ChassisBuilder implements Runnable {
    private CarQueue carQueue;
    private int counter = 0;

    public ChassisBuilder(CarQueue cq) {
        carQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(500);
                // Make chassis:
                Car c = new Car(counter++);
                print("ChassisBuilder created " + c);
                // Insert into queue
                carQueue.put(c);
            }
        } catch (InterruptedException e) {
            print("Interrupted: ChassisBuilder");
        }
        print("ChassisBuilder off");
    }
}

class Assembler implements Runnable {
    private CarQueue chassisQueue, finishingQueue;
    private Car car;
    private CyclicBarrier barrier = new CyclicBarrier(4);
    private RobotPool robotPool;

    public Assembler(CarQueue cq, CarQueue fq, RobotPool rp) {
        chassisQueue = cq;
        finishingQueue = fq;
        robotPool = rp;
    }

    public Car car() {
        return car;
    }

    public CyclicBarrier barrier() {
        return barrier;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until chassis is available:
                car = chassisQueue.take();
                // Hire robots to perform work:
                robotPool.hire(EngineRobot.class, this);        //这三道工序是顺序执行的，单其中可能被"阻塞"打断（而不是cpu的正常的线程调度）——- 错了，打错特错了。
                robotPool.hire(DriveTrainRobot.class, this);    //这三道工序的出发点是顺序的，即雇佣这个操作是顺序的。
                robotPool.hire(WheelRobot.class, this);         //但是这三个工序实际执行却没有一定的先后顺序。因为每调一次雇佣函数
                barrier.await(); // Until the robots finish        //就是一个返回（即使可能在线程池处因等待所要雇佣的工序而阻塞，但是阻塞完了后，也就返回了）。
                // Put car into finishingQueue for further work    //而是三个工序机器人又都是线程，他们被雇佣之前是一个"就绪"状态——不干本职工作，就阻塞在条件engage==false上。
                finishingQueue.put(car);                           //所以，一旦被雇佣，即被唤醒（唤醒前肯定要修改engage==true的，这是一个重点，之后还会讨论）后，就开始自己的"perform"本职工作。
                                                                   //工序机器人的本职工作不会被阻塞。
                                                                   //每个工序机器人完成了本职工作，就守在（专业词叫做阻塞）在CyclicBarrier处，等待其他工序和Assembler的到来——即他们也调用CyclicBarrier.await().
                                                                   //即，三个工序和组装工人这四个"伙伴"之间通过CyclicBarrier来协作。四个小伙伴都到了CyclicBarrier处后，各自做各自的事情了。哈哈。
                                                                   //其中，组装工人负责将car（此时的car已经拥有了"底座/引擎/传动装置/轮子"，在该程序中，一辆car即算完成了）放到finishingQueue中。
                                                                   //Reporter线程则从finishingQueue取出car并"报告"——消费。
            }
        } catch (InterruptedException e) {
            print("Exiting Assembler via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        print("Assembler off");
    }
}

class Reporter implements Runnable {
    private CarQueue carQueue;

    public Reporter(CarQueue cq) {
        carQueue = cq;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(carQueue.take());
            }
        } catch (InterruptedException e) {
            print("Exiting Reporter via interrupt");
        }
        print("Reporter off");
    }
}

abstract class Robot implements Runnable {
    private RobotPool pool;

    public Robot(RobotPool p) {
        pool = p;
    }

    protected Assembler assembler;

    public Robot assignAssembler(Assembler assembler) {
        this.assembler = assembler;
        return this;
    }

    private boolean engage = false;

    public synchronized void engage() {
        engage = true;
        notifyAll();
    }

    // The part of run() that's different for each robot:
    abstract protected void performService();

    public void run() {
        try {
            powerDown(); // Wait until needed
            while (!Thread.interrupted()) {
                performService();
                assembler.barrier().await(); // Synchronize
                // We're done with that job...等这其他工序-其他机器人在做-做完再一起向下走。
                powerDown();
            }
        } catch (InterruptedException e) {
            print("Exiting " + this + " via interrupt");
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
        print(this + " off");
    }

    private synchronized void
    powerDown() throws InterruptedException {
        engage = false;
        assembler = null; // Disconnect from the Assembler
        // Put ourselves back in the available pool:
        pool.release(this);
        while (engage == false)  // Power down
            wait();
    }

    public String toString() {
        return getClass().getName();
    }
}

class EngineRobot extends Robot {
    public EngineRobot(RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing engine");
        assembler.car().addEngine();
    }
}

class DriveTrainRobot extends Robot {
    public DriveTrainRobot(RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing DriveTrain");
        assembler.car().addDriveTrain();
    }
}

class WheelRobot extends Robot {
    public WheelRobot(RobotPool pool) {
        super(pool);
    }

    protected void performService() {
        print(this + " installing Wheels");
        assembler.car().addWheels();
    }
}

class RobotPool {
    // Quietly prevents identical entries:
    private Set<Robot> pool = new HashSet<Robot>();

    public synchronized void add(Robot r) {
        pool.add(r);
        notifyAll();
    }

    public synchronized void
    hire(Class<? extends Robot> robotType, Assembler d)
            throws InterruptedException {
        for (Robot r : pool)
            if (r.getClass().equals(robotType)) {
                pool.remove(r);
                r.assignAssembler(d);
                r.engage(); // Power it up to do the task
                return;
            }
        wait(); // None available
        hire(robotType, d); // Try again, recursively：正纳闷呢？wait()怎么没有配条件。
    }

    public synchronized void release(Robot r) {
        add(r);
    }
}

public class CarBuilder {
    public static void main(String[] args) throws Exception {
        CarQueue chassisQueue = new CarQueue(),
                finishingQueue = new CarQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        RobotPool robotPool = new RobotPool();      //可以多开几个"引擎机器人"，"传动装置机器人"，以及"轮子机器人"线程。
        exec.execute(new EngineRobot(robotPool));
        exec.execute(new DriveTrainRobot(robotPool));
        exec.execute(new WheelRobot(robotPool));
        exec.execute(new Assembler(
                chassisQueue, finishingQueue, robotPool));
        exec.execute(new Reporter(finishingQueue));
        // Start everything running by producing chassis:
        exec.execute(new ChassisBuilder(chassisQueue));
        TimeUnit.SECONDS.sleep(7);
        exec.shutdownNow();
    }
} /* (Execute to see output) *///:~
