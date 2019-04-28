package jdk.util.concurrent.family.lock;

import jdk.util.concurrent.family.common.Fruit;
import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author erik.wang
 * @date 2019/04/27
 **/
public class FamilyPlateGameLockVersion {

    /**
     * 这里面有很多细节。
     * 1.
     *
     * @param args
     */
    public static void main(String[] args) {


//      无论保留【1】【2】【3】那一句，程序都可以完美运行
//        Plate plate = new Plate(null);      //【1】
        Plate plate = new Plate(new Fruit("apple"));  //【2】
//        Plate plate = new Plate(new Fruit("orange")); //【3】

        ExecutorService executorService = Executors.newCachedThreadPool();

        Lock plateLock = new ReentrantLock();

        Condition plateIsEmptyCondition = plateLock.newCondition();
        Condition hasAnOrangeCondition = plateLock.newCondition();
        Condition hasAnAppleCondition = plateLock.newCondition();

        executorService.submit(new ThreadMother(plate, plateLock, plateIsEmptyCondition, hasAnOrangeCondition));
        executorService.submit(new ThreadFather(plate, plateLock, plateIsEmptyCondition, hasAnAppleCondition));
        executorService.submit(new ThreadSon(plate, plateLock, plateIsEmptyCondition, hasAnAppleCondition));
        executorService.submit(new ThreadDaughter(plate, plateLock, hasAnOrangeCondition, plateIsEmptyCondition));

        System.out.println("main thread sleep");

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdownNow();
        }
        System.out.println("main thread over!");

    }

}
