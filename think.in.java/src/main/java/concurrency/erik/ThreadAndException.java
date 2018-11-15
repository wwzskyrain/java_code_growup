package concurrency.erik;

import java.util.concurrent.*;

public class ThreadAndException {

//  1.  在线程池开启的线程内抛出了异常，是没有被捕获和处理的。
//  2.  get的话，线程内的异常就能被捕捉了。
    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        System.out.println("start submit new RunnableThrowException");
        Future<?> future = executorService.submit(new RunnableThrowException());

        executorService.shutdownNow();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main is over!");

    }

    public static class RunnableThrowException implements Runnable {
        @Override
        public void run() {
            System.out.println("run RunnableThrowException.run");
            throw new RuntimeException("from erik!");
        }
    }

}
