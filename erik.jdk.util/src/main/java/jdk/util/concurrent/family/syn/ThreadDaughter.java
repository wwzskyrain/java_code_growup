package jdk.util.concurrent.family.syn;

import jdk.util.concurrent.family.common.Plate;

public class ThreadDaughter extends Thread {

    Plate plate;

    ThreadDaughter(Plate plate) {
        this.plate = plate;
    }

    @Override
    public void run() {

        for (; ; ) {
            if (interrupted()) {
                System.out.println("【interrupted】 daughter thread is interrupted and return");
                break;
            }
            synchronized (plate) {
                while (plate.isEmpty() ||
                        !plate.getFruit().getName().equals("orange")) {
                    try {
                        plate.wait();
                    } catch (InterruptedException e) {
                        System.out.println("【wait】 daughter wait is interrupted and then return");
                        return;
                    }
                }

                System.out.println("daughter eat orange");
                plate.setFruit(null);
                plate.notifyAll();
// 无论要不要加一个短暂的休眠，程序都不会发生死锁。
//                try {
//                    TimeUnit.MILLISECONDS.sleep(Util.SLEEP_TIME);
//                } catch (InterruptedException e) {
//                    System.out.println("【sleep】daughter sleep is interrupted and then break");
//                    break;
//                }

            }
        }

    }

}