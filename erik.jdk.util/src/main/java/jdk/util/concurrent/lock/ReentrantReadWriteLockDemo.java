package jdk.util.concurrent.lock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author erik.wang
 * @date 2019/04/14
 **/
public class ReentrantReadWriteLockDemo {


    private Object object;

    private ReadWriteLock lock = new ReentrantReadWriteLock();

    public void get() throws InterruptedException {
        lock.readLock().lock();//上读锁
        try {
            System.out.println(Thread.currentThread().getName() + "准备读取数据");
            Thread.sleep(new Random().nextInt(1000));
            System.out.println(Thread.currentThread().getName() + "读数据为：" + this.object);
        } finally {
            lock.readLock().unlock();
        }
    }

    public void put(Object object) throws InterruptedException {
        lock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "准备写数据");
            Thread.sleep(new Random().nextInt(1000));
            this.object = object;
            System.out.println(Thread.currentThread().getName() + "写数据为" + this.object);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public static void main(String[] args) {

        ReentrantReadWriteLockDemo readWriteLockDemo = new ReentrantReadWriteLockDemo();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        try {
                            readWriteLockDemo.put(new Random().nextInt(1000));//写操作
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        for (int i = 0; i < 3; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = 0; j < 3; j++) {
                        try {
                            readWriteLockDemo.get();    //多个线程读取操作
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        executorService.shutdown();
    }

}
