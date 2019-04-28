package jdk.util.concurrent.family.lock;

import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadDaughter extends FamilyMember implements Runnable {

    private Condition hasAnOrangeCondition;
    private Condition plateIsEmptyCondition;

    public ThreadDaughter(Plate plate, Lock plateLock,
                          Condition hasAnOrangeCondition,
                          Condition plateIsEmptyCondition) {

        super(plate,plateLock);
        this.hasAnOrangeCondition = hasAnOrangeCondition;
        this.plateIsEmptyCondition = plateIsEmptyCondition;

    }

    @Override
    public void run() {

        for (; ; ) {

            if (Thread.currentThread().isInterrupted()) {
                System.out.println("【interrupted】 daughter thread is interrupted and return");
                break;
            }

            plateLock.lock();

            try {

                while (plate.isEmpty() ||
                        !getPlate().getFruit().getName().equals("orange")) {
                    try {
                        hasAnOrangeCondition.await();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 daughter wait is interrupted and then return");
                        return;
                    }
                }

                System.out.println("daughter eat orange");
                plate.setFruit(null);
                plateIsEmptyCondition.signalAll();

            } finally {
                plateLock.unlock();
            }

        }
    }

}