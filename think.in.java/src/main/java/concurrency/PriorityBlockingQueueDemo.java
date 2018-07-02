package concurrency;//: concurrency/PriorityBlockingQueueDemo.java

import java.util.concurrent.*;
import java.util.*;

import static net.mindview.util.Print.*;

class PrioritizedTask implements
        Runnable, Comparable<PrioritizedTask> {
    private Random rand = new Random(47);
    private static int counter = 0;
    private final int id = counter++;
    private final int priority;
    protected static List<PrioritizedTask> sequence =
            new ArrayList<PrioritizedTask>();

    public PrioritizedTask(int priority) {
        this.priority = priority;
        sequence.add(this);
    }

    public int compareTo(PrioritizedTask arg) {
        return priority < arg.priority ? 1 :
                (priority > arg.priority ? -1 : 0);
    }

    public void run() {
        try {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(250));
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print(this);
    }

    public String toString() {
        return String.format("[%1$-3d]", priority) +
                " Task " + id;
    }

    public String summary() {
        return "(" + id + ":" + priority + ")";
    }

    public static class EndSentinel extends PrioritizedTask {
        private ExecutorService exec;

        public EndSentinel(ExecutorService e) {
            super(-1); // Lowest priority in this program
            exec = e;
        }

        public void run() {
            int count = 0;
            for (PrioritizedTask pt : sequence) {
                printnb(pt.summary());
                if (++count % 5 == 0)
                    print();
            }
            print();
            print(this + " Calling shutdownNow()");
            exec.shutdownNow();
        }
    }
}

class PrioritizedTaskProducer implements Runnable {
    private Random rand = new Random(47);
    private Queue<Runnable> queue;
    private ExecutorService exec;

    public PrioritizedTaskProducer(
            Queue<Runnable> q, ExecutorService e) {
        queue = q;
        exec = e; // Used for EndSentinel
    }

    public void run() {
        // Unbounded queue; never blocks.
        // Fill it up fast with random priorities:
        for (int i = 0; i < 20; i++) {
            queue.add(new PrioritizedTask(rand.nextInt(10)));
            Thread.yield();
        }
        // Trickle in highest-priority jobs:
        try {
            for (int i = 0; i < 10; i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                queue.add(new PrioritizedTask(10));
            }
            // Add jobs, lowest priority first:
            for (int i = 0; i < 10; i++)
                queue.add(new PrioritizedTask(i));
            // A sentinel to stop all the tasks:
            queue.add(new PrioritizedTask.EndSentinel(exec));
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print("Finished PrioritizedTaskProducer");
    }
}

class PrioritizedTaskConsumer implements Runnable {
    private PriorityBlockingQueue<Runnable> q;

    public PrioritizedTaskConsumer(
            PriorityBlockingQueue<Runnable> q) {
        this.q = q;
    }

    public void run() {
        try {
            while (!Thread.interrupted())
                // Use current thread to run the task:
                q.take().run();
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print("Finished PrioritizedTaskConsumer");
    }
}
//  通过读api可知PriorityBlockingQueue调用Comparable接口来判断优先级的，如果队列中的元素没有实现该接口，则报CastClassException。
//  该Demo是用一个生产者线程来构造任务并向"优先级阻塞队列"添加任务，而消费者线程从"优先级阻塞队列"中获取take任务并执行任务。当执行到EndSentinel任务后，EndSentinel任务回关闭程序。
//  这个Demo的结构同DelayQueueDemo。这里EndSentinel必须保证是最后一个被取出队列，所以他的优先级必须最小（这里为-1）。

public class PriorityBlockingQueueDemo {
    public static void main(String[] args) throws Exception {
        Random rand = new Random(47);
        ExecutorService exec = Executors.newCachedThreadPool();
        PriorityBlockingQueue<Runnable> queue =
                new PriorityBlockingQueue<Runnable>();
        exec.execute(new PrioritizedTaskProducer(queue, exec));
        exec.execute(new PrioritizedTaskConsumer(queue));
    }
} /* (Execute to see output) *///:~
