package concurrency;//: concurrency/HorseRace.java
// Using CyclicBarriers.

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

class Horse implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b) {
        barrier = b;
    }

    public synchronized int getStrides() {
        return strides;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    strides += rand.nextInt(10); // Produces 0, 1 or 2
                }
                System.out.printf("thread %s invoke barrier.await()\n", Thread.currentThread().getName());
                int numberArrived = barrier.await();
//  等随后一个party调用await时，这些阻塞在barrier上的线程都解除阻塞了
                System.out.printf("thread %s arrived No %d\n", Thread.currentThread().getName(), numberArrived);
            }
            System.out.printf("while over for %s at %s\n", Thread.currentThread().getName(), new Date().getTime());
        } catch (InterruptedException e) {
            // A legitimate way to exit

            System.out.printf("interruptedException for %s at %s\n", Thread.currentThread().getName(), new Date().getTime());
        } catch (BrokenBarrierException e) {
            // This one we want to know about
            throw new RuntimeException(e);
        }
    }

    public String toString() {
        return "Horse " + id + " ";
    }

    public String tracks() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++)
            s.append("*");
        s.append(id);
        return s.toString();
    }
}

public class HorseRace {
    static final int FINISH_LINE = 75;
    private List<Horse> horses = new ArrayList<Horse>();
    private ExecutorService exec =
            Executors.newCachedThreadPool();
    private CyclicBarrier barrier;

    public HorseRace(int nHorses, final int pause) {
        barrier = new CyclicBarrier(nHorses, new Runnable() {
            public void run() {
//在赛马中，这是个（报告进度+裁决胜利者）的任务。七匹马都跑了一个单位时间后，该任务执行；前期该任务只有报告进度，最后冲破终点时，裁决胜利者。

                System.out.printf("report from thread[%s] as follow.\n", Thread.currentThread().getName());
                StringBuilder s = new StringBuilder();
                for (int i = 0; i < FINISH_LINE; i++) {
                    s.append("="); // The fence on the racetrack
                }
                s.append("\n");
                for (Horse horse : horses) {
                    s.append(horse.tracks()).append("\n");
                }
                print(s);

                List<Horse> wins = new ArrayList<>();
                for (Horse horse : horses) {    //遍历是不公平的：如果同时到达，那么编号在前的马将胜出。
                    if (horse.getStrides() >= FINISH_LINE) {
//在一个"单位时间"之后，可能会有多匹马冲到终点；由于该单位时间的精度，所以不能再提供更精准的"裁决"了。
//只能让他们都作为"胜利者"。
                        wins.add(horse);
                    }
                }
                if (wins.size() > 0) {
                    print("wins:" + wins);
                    exec.shutdownNow(); //tag-1：设置众线程的中断标志位。

                    System.out.printf("thread %s invoke shutdown() at %s \n", Thread.currentThread().getName(), new Date().getTime());
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(pause);
                } catch (InterruptedException e) {
                    System.out.printf("barrier-action sleep interrupted, report from thread[%s]\n", Thread.currentThread().getName());
                    exec.shutdownNow(); //不加这句话，程序结束不了。怪怪的
//                  总算知道原因了：不加这句话的时候，剩下一个线程t，就是运行该barrierAction的线程，也就是本轮最后一个调用barrier.await的那个线程。
//                  t就会阻塞在下一轮的barrier.await方法上。为什么没有跳出horse的while循环，因为catch将当前线程t的中断标志清除了。中断标志来自于tag-1。

//                  那么，有没有其他办法（非exec.shutdownNow）来终结最后一个线程呢？
// 分析：既然是"中断标志位"被清楚，那就在设置一次。试验了下，有效；Thread.currentThread().interrupt();
                }
            }
        });
        for (int i = 0; i < nHorses; i++) {
            Horse horse = new Horse(barrier);
            horses.add(horse);
            exec.execute(horse);
        }
    }

    public static void main(String[] args) {
        int nHorses = 7;
        int pause = 500;
        if (args.length > 0) { // Optional argument
            int n = new Integer(args[0]);
            nHorses = n > 0 ? n : nHorses;
        }
        if (args.length > 1) { // Optional argument
            int p = new Integer(args[1]);
            pause = p > -1 ? p : pause;
        }
        new HorseRace(nHorses, pause);
    }

//    WWZ：问题来了，当线程调用await到达指定个数时，是await先返回，还是先执行barrierAction？应该是后者吧。
//    WWZ：最后一个线程负责调用barrierAction这个Runnable。
//    WWZ：没办法用shutdownNow来给所处线程发中断指令。错，一样可以。不信的话，注释掉barrierAction中的try-catch。
//    WWZ：最后一个线程到达的时候，之前等待在await上的线程都从await调用处"被唤醒"了，然后，他们等待的只是被CPU调度，而调度终会到来，而且调度本身不会被中断。
//
} /* (Execute to see output) *///:~
