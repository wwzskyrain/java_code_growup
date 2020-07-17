package jdk.util.concurrent.aqs;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author erik.wang
 * @date 2020-06-23 23:03
 */
public class ReentrantLockTest {

    public static class MyCountThread extends Thread {

        private Lock myLock;
        private AtomicLong count;

        public MyCountThread(Lock myLock, AtomicLong count) {
            this.myLock = myLock;
            this.count = count;
        }

        @Override
        public void run() {
            count();
        }

        public void count() {
            myLock.lock();
            try {
                long c = this.count.incrementAndGet();
                System.out.printf("%s . count=%d\n", Thread.currentThread().getName(), c);
            } finally {
                myLock.unlock();
            }
        }

    }

    public static void main(String[] args) {

        ReentrantLock lock = new ReentrantLock(false);
        AtomicLong count = new AtomicLong();
        //非公平的释放是fifo的，其非公平性体现在入队时要不要判断队列中是否有阻塞结点。
        lock.lock();
        try {
            for (int i = 0; i < 100; i++) {
                MyCountThread myCountThread = new MyCountThread(lock, count);
                myCountThread.start();
            }
        } finally {
            lock.unlock();
            System.out.println("unlock()");
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("count:" + count);


    }


}
