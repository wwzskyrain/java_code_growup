package jdk.util.concurrent.thread.pool;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @date 2020-06-29 08:50
 */
public class ThreadPoolTest {

    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolTest.class);

    private final static ExecutorService executor = new ThreadPoolExecutor(
            5,
            100,
            20, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20));


    /**
     * 测试，在任务数占满队列之前是不会开辟大于core_size的那部分线程的
     */
    @Test
    public void test_enqueue_before_maximum_size() {
        for (int i = 0; i < 25; i++) {
            int k = i;
            executor.submit(() -> {

                try {
                    TimeUnit.SECONDS.sleep(1);
                    logger.info("sleep over {}", k);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });
            logger.info("submit {}", i);
        }

        executor.shutdown();
        logger.info("over");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {


    }


}
