package jdk.util.concurrent.lock;

import jdk.util.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Date 2019-08-19
 * @Created by erik
 */
public class ReadWriteDemo {


    class ReadThread extends Thread {
        private ReadWriteLock lock;

        ReadThread(String threadName, ReadWriteLock lock) {
            super(threadName);
            this.lock = lock;
        }

        @Override
        public void run() {
            Logger.log("I need read lock to read");
            lock.readLock().lock();
            Logger.log("I acquired a readLock and  start reading and I need 5 seconds");
            try {
                TimeUnit.SECONDS.sleep(5);
                Logger.log("end reading and It takes 5 seconds");
            } catch (InterruptedException e) {
                Logger.log("I am wake up by sleep");
            } finally {
                lock.readLock().unlock();
            }

        }
    }

    class WriteThread extends Thread {
        private ReadWriteLock lock;

        WriteThread(String threadName, ReadWriteLock lock) {
            super(threadName);
            this.lock = lock;
        }

        @Override
        public void run() {
            Logger.log("I need write lock to read");
            lock.writeLock().lock();
            Logger.log("I acquired a writeLock and  start write and I need 5 seconds");
            try {
                TimeUnit.SECONDS.sleep(5);
                Logger.log("end write and It takes 5 seconds");
            } catch (InterruptedException e) {
                Logger.log("I am wake up by sleep");
            } finally {
                lock.writeLock().unlock();
            }

        }
    }

    public static void main(String[] args) {

        ReadWriteDemo readWriteDemo = new ReadWriteDemo();
        ReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        ReadThread readerA = readWriteDemo.new ReadThread("readerA", readWriteLock);
        ReadThread readerB = readWriteDemo.new ReadThread("readerB", readWriteLock);
        ReadThread readerC = readWriteDemo.new ReadThread("readerC", readWriteLock);

        WriteThread writerD = readWriteDemo.new WriteThread("writerD", readWriteLock);

        ReadThread readerE = readWriteDemo.new ReadThread("readerE", readWriteLock);
        ReadThread readerF = readWriteDemo.new ReadThread("readerF", readWriteLock);

        readerA.start();
        readerB.start();
        readerC.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        writerD.start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        readerE.start();
        readerF.start();
        // 因为是一个公平锁，而读线程EF都晚于写线程D入队，所以D先获得写锁，并阻塞EF它写的时间
        Logger.log("main over!");


    }


}
