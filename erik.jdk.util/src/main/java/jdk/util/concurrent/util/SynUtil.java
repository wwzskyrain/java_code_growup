package jdk.util.concurrent.util;

import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author erik.wang
 * @date 2019/05/10
 **/
public class SynUtil {


    @Test
    public void test_count_down_latch() {

        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                try {
                    System.out.printf("thread %s wait at latch\n", Thread.currentThread().getName());
                    countDownLatch.await();
                    System.out.printf("thread %s pass over latch.\n", Thread.currentThread().getName());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            System.out.printf("thread %s countDown %d time.\n", Thread.currentThread().getName(), i);
            countDownLatch.countDown();

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("main thread over.");

    }

    @Test
    public void test_cyclic_barrier() {

        int partiesCount = 5;
        CyclicBarrier cyclicBarrier = new CyclicBarrier(partiesCount, () -> {

            System.out.println("==================");
            System.out.println("2 second to wait to say next.");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        AtomicInteger atomicInteger = new AtomicInteger();

        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < partiesCount; i++) {

            executorService.submit(() -> {
                while (true) {

                    Random random = new Random(System.currentTimeMillis() % 1000);
                    int span = random.nextInt(10);
                    atomicInteger.addAndGet(span);
                    System.out.printf("thread %s atomicInteger:%d ,addAndGet:%d \n",
                            Thread.currentThread().getName(), atomicInteger.get(), span);
                    try {
                        cyclicBarrier.await();
                    } catch (InterruptedException e) {
                        System.out.printf("thread %s is interrupted.\n");

                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }

            });
        }

        try {
            TimeUnit.SECONDS.sleep(10);
            executorService.shutdownNow();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
