package jdk.util.concurrent.thread;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

import static java.lang.Thread.sleep;

/**
 * @Date 2019-08-18
 * @Created by erik
 */
public class InterruptThreadDemo {

    class WaitedThread extends Thread {

        @Override
        public void run() {
            synchronized (this) {
                try {
                    log("I will wait()");
                    wait();
                } catch (InterruptedException e) {
                    log("i am waiting but facing interruptexception now");
                }
            }
        }
    }

    /**
     * 测试 wait阻塞的线程可以相应interrupt。为什么呢，底层操作系统搞的鬼
     *
     * @throws InterruptedException
     */
    void test_WaitedThread() throws InterruptedException {
        Thread waitedThread = new InterruptThreadDemo().new WaitedThread();
        waitedThread.start();
        log("test_WaitedThread start.");

        TimeUnit.SECONDS.sleep(5);
        waitedThread.interrupt();
        log("test_WaitedThread after 5 second. thread state:" + waitedThread.getState());

    }

    class SleepThread extends Thread {

        @Override
        public void run() {
            try {
                log("I will sleep 10 seconds");
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                log("I am sleep but wake up.");
            }
        }
    }

    void test_SleepThread() throws InterruptedException {
        SleepThread sleepThread = new InterruptThreadDemo().new SleepThread();
        sleepThread.start();
        log("test_SleepThread start");
        TimeUnit.SECONDS.sleep(5);
        sleepThread.interrupt();
        log("test_SleepThread end");

    }

    class ParkedThread extends Thread {

        @Override
        public void run() {
            log("I will be parked.");
            LockSupport.parkNanos(10 * 1000 * 1000 * 1000L);
            log("I am going after parkNanos");
        }
    }

    void test_ParkedThread() throws InterruptedException {
        ParkedThread parkedThread = new InterruptThreadDemo().new ParkedThread();
        parkedThread.start();
        log("test_ParkedThread start");
        TimeUnit.SECONDS.sleep(5);
        parkedThread.interrupt();
        log("test_ParkedThread end");
    }

    class SynchronizedThread extends Thread{

        @Override
        public void run() {
            synchronized (SynchronizedThread.class){
                log("thread "+Thread.currentThread() +" enter synchronized and will sleep 10 second.");
                try {
                    // attention1 没办法中断一个被synchronize阻塞的线程。synchronizedThread2.interrupt()只有在这里才起作用，
//                    即线程synchronizedThread2进入睡眠时。
                    TimeUnit.SECONDS.sleep(10);
                    log("thread "+Thread.currentThread() +" entered synchronized block sleep over.");
                } catch (InterruptedException e) {
                    log("thread "+Thread.currentThread() +" entered synchronized block sleep interrupted.");
                }
            }
        }
    }

    void test_SynchronizedThread() throws InterruptedException {
        SynchronizedThread synchronizedThread1  = new InterruptThreadDemo().new SynchronizedThread();
        SynchronizedThread synchronizedThread2  = new InterruptThreadDemo().new SynchronizedThread();

        log("test_SynchronizedThread start");
        synchronizedThread1.start();
        synchronizedThread2.start();

        TimeUnit.SECONDS.sleep(5);
        synchronizedThread2.interrupt();
        log("test_SynchronizedThread end");
    }

    public static void main(String[] args) throws InterruptedException {

//        new InterruptThreadDemo().test_WaitedThread();
//        new InterruptThreadDemo().test_SleepThread();
//        new InterruptThreadDemo().test_ParkedThread();
        new InterruptThreadDemo().test_SynchronizedThread();
    }


    public static void log(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        System.out.printf("%s %s\n", simpleDateFormat.format(new Date()), message);
    }


}
