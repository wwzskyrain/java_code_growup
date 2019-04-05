package thread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author erik.wang
 * @date 2019/03/28
 **/
public class Main {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        executorService.execute(() -> {
            System.out.println(Thread.currentThread());
        });

        ExecutorService executorService1 = Executors.newWorkStealingPool();

    }

}
