package jdk.util.concurrent.family.lock;

import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadSon extends FamilyMember implements Runnable {

    private Condition hasAppleCondition;
    private Condition plateIsEmptyCondition;

    public ThreadSon(Plate plate,
                     Lock plateLock,
                     Condition plateIsEmptyCondition,
                     Condition hasAppleCondition) {
        super(plate, plateLock);
        this.plateIsEmptyCondition = plateIsEmptyCondition;
        this.hasAppleCondition = hasAppleCondition;
    }

    @Override
    public void run() {

        for (; ; ) {


            if (Thread.currentThread().interrupted()) {
                System.out.println("【interrupted】 son thread is interrupted and return");
                break;
            }


            plateLock.lock();

            try {

                while (plate.isEmpty() ||
                        !plate.getFruit().getName().equals("apple")) {
                    try {
                        hasAppleCondition.await();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 son wait is interrupted and then return");
                        return;
                    }
                }

                System.out.println("son eat apple");
                plate.setFruit(null);
                plateIsEmptyCondition.signalAll();

            } finally {
                plateLock.unlock();
            }

        }
    }
}
