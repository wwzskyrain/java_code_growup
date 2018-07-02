package concurrency;//: concurrency/Interrupting.java
// Interrupting a blocked thread.

import java.util.concurrent.*;
import java.io.*;

import static net.mindview.util.Print.*;

class SleepBlocked implements Runnable {
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            print("InterruptedException");
        }
        print("Exiting SleepBlocked.run()");
    }
}

class IOBlocked implements Runnable {
    private InputStream in;

    public IOBlocked(InputStream is) {
        in = is;
    }

    public void run() {
        try {
            print("Waiting for read():");
            in.read();
        } catch (Exception e) {

//          wwz：如果出现异常，那么当前线程的中断状态是？
//  异常处理的代码也是在当前线程中执行的。
            System.out.printf("catch code in IOBlocked. is：%s,thread name:%s \n", in.getClass().getName(), Thread.currentThread().getName());
            if (Thread.currentThread().isInterrupted()) {
                print("Interrupted from blocked I/O");
            } else {
                System.out.printf("当前线程是非中断状态：%s", Thread.currentThread().isInterrupted());
//                throw new RuntimeException(e);
            }
        }


        print("Exiting IOBlocked.run()");
    }
}

class SynchronizedBlocked implements Runnable {
    public synchronized void f() {  //wwz：单从语法上就看出，synchronized阻塞是不可中断的。
        while (true) // Never releases lock
            Thread.yield();
    }

    public SynchronizedBlocked() {
        new Thread() {
            public void run() {
                f(); // Lock acquired by this thread
            }
        }.start();
    }

    public void run() {
        print("Trying to call f()");
        f();
        print("Exiting SynchronizedBlocked.run()");
    }
}

class WaitBlocked implements Runnable {

    Object lock = new Object();

    public WaitBlocked() {

    }

    @Override
    public void run() {
        System.out.printf("before WaitBlocked synchronize.");
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
//WWZ：wait是一个可中断阻塞。
                System.out.printf("wait block has been interrupted!\n");
//                e.printStackTrace();
            }
        }



    }
}

public class Interrupting {
    private static ExecutorService exec =
            Executors.newCachedThreadPool();

    static void test(Runnable r) throws InterruptedException {
        Future<?> f = exec.submit(r);
        TimeUnit.MILLISECONDS.sleep(1000);
        print("Interrupting " + r.getClass().getName());
        f.cancel(true); // Interrupts if running
        print("Interrupt sent to " + r.getClass().getName());
    }

    public static void main(String[] args) throws Exception {
        test(new SleepBlocked());
        test(new IOBlocked(System.in));
        test(new SynchronizedBlocked());
        test(new WaitBlocked());
        TimeUnit.SECONDS.sleep(5);
        print("Aborting with System.exit(0)");
        System.exit(0); // ... since last 2 interrupts failed
    }
} /* Output: (95% match)
Interrupting SleepBlocked
InterruptedException
Exiting SleepBlocked.run()
Interrupt sent to SleepBlocked
Waiting for read():
Interrupting IOBlocked
Interrupt sent to IOBlocked
Trying to call f()
Interrupting SynchronizedBlocked
Interrupt sent to SynchronizedBlocked
Aborting with System.exit(0)
*///:~
