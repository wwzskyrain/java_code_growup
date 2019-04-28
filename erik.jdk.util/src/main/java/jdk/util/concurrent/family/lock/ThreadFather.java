package jdk.util.concurrent.family.lock;

import jdk.util.concurrent.family.common.Fruit;
import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadFather extends FamilyMember implements Runnable {

    private Condition plateEmptyCondition;
    private Condition hasAppleCondition;

    public ThreadFather(Plate plate,
                        Lock plateLock,
                        Condition plateEmptyCondition,
                        Condition hasAppleCondition) {
        super(plate, plateLock);
        this.plateEmptyCondition = plateEmptyCondition;
        this.hasAppleCondition = hasAppleCondition;

    }

    @Override
    public void run() {

        for (; ; ) {


            if (Thread.currentThread().interrupted()) {
                System.out.println("【interrupted】 father thread is interrupted and return");
                break;
            }

            plateLock.lock();

            try {

                while (!plate.isEmpty()) {
                    try {
                        plateEmptyCondition.await();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 father wait is interrupted and then return");
                        return;
                    }

                }
                plate.setFruit(new Fruit("apple"));
                System.out.println("father place a apple");
//                hasAppleCondition.signalAll();  //signalAll或者signal都可以的。
                hasAppleCondition.signal();
            } finally {
                plateLock.unlock();
            }
        }
    }

}