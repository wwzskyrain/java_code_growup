package jdk.util.concurrent.family.syn;

import jdk.util.concurrent.family.common.Plate;

public class ThreadSon extends Thread {

    Plate plate;

    ThreadSon(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {

        for (; ; ) {

            if (interrupted()) {
                System.out.println("【interrupted】 son thread is interrupted and return");
                break;
            }

            synchronized (plate) {
                while (plate.isEmpty() ||
                        !plate.getFruit().getName().equals("apple")) {

                    try {
                       plate.wait();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 son wait is interrupted and then return");
                        return;
                    }
                }

                System.out.println("son eat apple");
                plate.setFruit(null);
                plate.notifyAll();

//                try {
//                    TimeUnit.MILLISECONDS.sleep(Util.SLEEP_TIME);
//                } catch (InterruptedException e) {
//                    System.out.println("【sleep】son sleep is interrupted and then break");
//                    break;
//                }
            }
        }
    }
}
