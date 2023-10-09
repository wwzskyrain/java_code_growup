package concurrency;//: concurrency/CountDownLatchDemo.java

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

// Performs some portion of a task:
class TaskPortion implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private static Random rand = new Random(47);
    private final CountDownLatch latch;

    TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            doWork();
            //调用够x个次之后，哪些await()的线程才能执行下去。
            latch.countDown();
        } catch (InterruptedException ex) {
            // Acceptable way to exit
        }
    }

    public void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(2000));
        print(this + "completed");
    }

    public String toString() {
        return String.format("%1$-3d ", id);
    }
}

// Waits on the CountDownLatch:
class WaitingTask implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final CountDownLatch latch;

    WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            print("Latch barrier before is " + this);
            latch.await();
            print("Latch barrier passed for " + this);
        } catch (InterruptedException ex) {
            print(this + " interrupted");
        }
    }

    public String toString() {
        return String.format("WaitingTask %1$-3d ", id);
    }
}

public class CountDownLatchDemo {
    static final int SIZE = 100;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        // All must share a single CountDownLatch object:
        CountDownLatch latch = new CountDownLatch(SIZE);    //wwz：这个数字被设置为0是就失去了其本身的意义。
        for (int i = 0; i < 10; i++)
            exec.execute(new WaitingTask(latch));
        for (int i = 0; i < SIZE; i++)  //CountDownLatch把任务分成了前后两类，前后有次序关系，即所有的前期工作做完了，后期工作才开始做。
            exec.execute(new TaskPortion(latch));
        print("Launched all tasks");
        exec.shutdown(); // Quit when all tasks complete
    }

/*  "开闸"后，后期任务们可是无序执行的。下面是某一次的运行输出（开闸后）
Latch barrier passed for WaitingTask 3
Latch barrier passed for WaitingTask 1
Latch barrier passed for WaitingTask 5
Latch barrier passed for WaitingTask 6
Latch barrier passed for WaitingTask 2
Latch barrier passed for WaitingTask 0
Latch barrier passed for WaitingTask 8
Latch barrier passed for WaitingTask 7
Latch barrier passed for WaitingTask 4
Latch barrier passed for WaitingTask 9

* */


/*下面是另一测运行输出（开闸后）

Latch barrier passed for WaitingTask 2
Latch barrier passed for WaitingTask 7
Latch barrier passed for WaitingTask 1
Latch barrier passed for WaitingTask 4
Latch barrier passed for WaitingTask 0
Latch barrier passed for WaitingTask 5
Latch barrier passed for WaitingTask 9
Latch barrier passed for WaitingTask 8
Latch barrier passed for WaitingTask 3
Latch barrier passed for WaitingTask 6

* */

} /* (Execute to see output) *///:~
