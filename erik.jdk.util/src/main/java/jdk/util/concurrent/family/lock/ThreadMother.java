package jdk.util.concurrent.family.lock;

import jdk.util.concurrent.family.common.Fruit;
import jdk.util.concurrent.family.common.Plate;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class ThreadMother extends FamilyMember implements Runnable {

    private Condition plateIsEmpty;
    private Condition hasAnOrangeCondition;

    public ThreadMother(Plate plate,
                        Lock plateLock,
                        Condition plateIsEmpty,
                        Condition hasAnOrangeCondition) {
        super(plate, plateLock);

        this.plateIsEmpty = plateIsEmpty;
        this.hasAnOrangeCondition = hasAnOrangeCondition;
    }

    @Override
    public void run() {

        for (; ; ) {

            if (Thread.currentThread().interrupted()) {
                System.out.println("【interrupted】 mother thread is interrupted and return");
                break;
            }

            plateLock.lock();

            try {
                while (!plate.isEmpty()) {
                    try {
                        plateIsEmpty.await();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 mother wait is interrupted and then return");
                        return;
                    }
                }

                plate.setFruit(new Fruit("orange"));
                System.out.println("mother place an orange.");
//                hasAnOrangeCondition.signalAll();  //signalAll或者signal都可以的。
                hasAnOrangeCondition.signal();

            } finally {
                plateLock.unlock();
            }

        }

    }
}