package concurrency;//: concurrency/DelayQueueDemo.java

import java.util.concurrent.*;
import java.util.*;

import static java.util.concurrent.TimeUnit.*;
import static net.mindview.util.Print.*;

class DelayedTask implements Runnable, Delayed {

    private static int counter = 0;
    private final int id = counter++;
    private final int delta;
    private final long trigger;
    protected static List<DelayedTask> sequence =
            new ArrayList<DelayedTask>();

    public DelayedTask(int delayInMilliseconds) {
        delta = delayInMilliseconds;
        trigger = System.nanoTime() +
                NANOSECONDS.convert(delta, MILLISECONDS);
        sequence.add(this);
    }

    public long getDelay(TimeUnit unit) {
        return unit.convert(
                trigger - System.nanoTime(), NANOSECONDS);
    }

    public int compareTo(Delayed arg) {
        DelayedTask that = (DelayedTask) arg;
        if (trigger < that.trigger) return -1;
        if (trigger > that.trigger) return 1;
        return 0;
    }

    public void run() {
        printnb(this + " at thread:"+Thread.currentThread().getName()+"\n");
    }

    public String toString() {
        return String.format("[%1$-4d]", delta) +
                " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + delta + ")";
    }

    public static class EndSentinel extends DelayedTask {
        private ExecutorService exec;

        public EndSentinel(int delay, ExecutorService e) {
            super(delay);
            exec = e;
        }

        public void run() {
            print();
            for (DelayedTask pt : sequence) {
                printnb(pt.summary() + " ");
            }
            print();
//            print(this + " Calling shutdownNow()");
//            exec.shutdownNow();//奇怪的很，调用shutdownNow就可以正常关闭程序。
//          而调用Thread.interrupt就不能正常关闭程序，线程pool-1-thread-1在 java.util.concurrent.SynchronousQueue$TransferStack@1147465f上TIMED_WAITING
//          因为这里只有一个消费线程，所以完全可以是用Thread.interrupt。
            System.out.printf("thread[%s] invoke interrupt.\n",Thread.currentThread().getName());
            Thread.currentThread().interrupt();   //消费线程也确实正常执行完了，但是就是线程不结束。
        }
    }
}

class DelayedTaskConsumer implements Runnable {
    private DelayQueue<DelayedTask> q;

    public DelayedTaskConsumer(DelayQueue<DelayedTask> q) {
        this.q = q;
    }

    public void run() {
        try {
            while (!Thread.interrupted()) {

                System.out.printf("thread[%s] will take a delay_task\n",Thread.currentThread().getName());
                DelayedTask delayedTask = q.take(); // Run task with the current thread
//                TimeUnit.MILLISECONDS.sleep(500);
                System.out.printf("thread[%s] take a delay_task[%s] and run it:",Thread.currentThread().getName(),delayedTask);
                delayedTask.run();
            }

        } catch (InterruptedException e) {
            // Acceptable way to exit
            System.out.printf("catch interrupt thread[%s]\n",Thread.currentThread().getName());
        }
        print("Finished DelayedTaskConsumer");
    }
}

public class DelayQueueDemo {
    public static void main(String[] args) {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<DelayedTask> queue =
                new DelayQueue<DelayedTask>();

        exec.execute(new DelayedTaskConsumer(queue));   //移动到这儿。

        // Fill with tasks that have random delays:
        for (int i = 0; i < 20; i++)
            queue.put(new DelayedTask(rand.nextInt(5000)));
        // Set the stopping point
        queue.add(new DelayedTask.EndSentinel(6000, exec));

        System.out.printf("thread[%s] is over \n",Thread.currentThread().getName());

    }
} /* Output:
[128 ] Task 11 [200 ] Task 7 [429 ] Task 5 [520 ] Task 18 [555 ] Task 1 [961 ] Task 4 [998 ] Task 16 [1207] Task 9 [1693] Task 2 [1809] Task 14 [1861] Task 3 [2278] Task 15 [3288] Task 10 [3551] Task 12 [4258] Task 0 [4258] Task 19 [4522] Task 8 [4589] Task 13 [4861] Task 17 [4868] Task 6 (0:4258) (1:555) (2:1693) (3:1861) (4:961) (5:429) (6:4868) (7:200) (8:4522) (9:1207) (10:3288) (11:128) (12:3551) (13:4589) (14:1809) (15:2278) (16:998) (17:4861) (18:520) (19:4258) (20:5000)
[5000] Task 20 Calling shutdownNow()
Finished DelayedTaskConsumer
*///:~
