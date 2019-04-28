package jdk.util.concurrent.family.syn;

import jdk.util.concurrent.family.common.Fruit;
import jdk.util.concurrent.family.common.Plate;

public class ThreadMother extends Thread {

    Plate plate;

    ThreadMother(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {


        for (; ; ) {

            if (interrupted()) {
                System.out.println("【interrupted】 mother thread is interrupted and return");
                break;
            }

            synchronized (plate) {
                while (!plate.isEmpty()) {
                    try {
                        plate.wait();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 mother wait is interrupted and then return");
                        return;
                    }
                }

                plate.setFruit(new Fruit("orange"));
                System.out.println("mother place an orange.");
                plate.notifyAll();

//                try {
//                    TimeUnit.MILLISECONDS.sleep(Util.SLEEP_TIME);
//                } catch (InterruptedException e) {
//                    System.out.println("【sleep】mother sleep is interrupted and then break");
//                    break;
//                }

            }

        }

    }
}