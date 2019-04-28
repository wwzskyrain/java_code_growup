package jdk.util.concurrent.family.syn;

import jdk.util.concurrent.family.common.Fruit;
import jdk.util.concurrent.family.common.Plate;

public class ThreadFather extends Thread {

    Plate plate;

    ThreadFather(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {

        for (; ; ) {
            if (interrupted()) {
                System.out.println("【interrupted】 father thread is interrupted and return");
                break;
            }
            synchronized (plate) {
                while (!plate.isEmpty()) {
                    try {
                        plate.wait();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 father wait is interrupted and then return");
                        return;
                    }
                }

                plate.setFruit(new Fruit("apple"));
                System.out.println("father place a apple");
                plate.notifyAll();

//                try {
//                    TimeUnit.MILLISECONDS.sleep(Util.SLEEP_TIME);
//                } catch (InterruptedException e) {
//                    System.out.println("【sleep】father sleep is interrupted and then break");
//                    break;
//                }
            }
        }
    }

}