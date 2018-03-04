package concurrency.erik;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;

class CallableTask implements Callable<String> {

    private static final SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

    private int taskId;

    public CallableTask(int taskId) {
        this.taskId = taskId;
    }

    @Override
    public String call() throws Exception {

        TimeUnit.SECONDS.sleep(taskId);

        System.out.printf("ready return taskId=%d time=%s\n", taskId, format.format(new Date()));
        return String.format("task id=%d returned", taskId);
    }
}

class ReportResult<V> implements Runnable {

    BlockingQueue blockingQueue;

    Future<V> future;

    public ReportResult(BlockingQueue blockingQueue, Future<V> future) {
        this.blockingQueue = blockingQueue;
        this.future = future;
    }

    @Override
    public void run() {

        try {
            V result = future.get();

            blockingQueue.add(result);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}

public class ConsumerCallableResultImmediately {


    public static void main(String[] args) {

//        test_polling();
        test_thread();

    }

    public static void test_thread() {

        ExecutorService executor = Executors.newCachedThreadPool();

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss");

        BlockingQueue<String> blockingQueue = new LinkedBlockingDeque<>();

        for (int i = 0; i < 10; i++) {
            Future<String> future = executor.submit(new CallableTask(i));

            executor.submit(new ReportResult(blockingQueue, future));   //两种启动"报告线程"的方式，用"执行器"更好一些吧。
//            new Thread(new ReportResult(blockingQueue, future)).start();
        }

        executor.shutdown();    //线程池不在接收新的任务了，等已经接收的任务都执行完毕，线程池也就关闭了。

        int counter = 0;
        while (counter < 10) {
            try {
                System.out.printf("get result=%s at time=%s\n", blockingQueue.take(), format.format(new Date()));
                counter++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void test_polling() {  //轮询法
        ExecutorService executor = Executors.newCachedThreadPool();

        List<Future<String>> futures = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            futures.add(executor.submit(new CallableTask(i)));
        }

        executor.shutdown();    //线程池不在接收新的任务了，等已经接收的任务都执行完毕，线程池也就关闭了。

        int counter = 0;

        boolean[] idDone = new boolean[10];
        while (counter < 10) {

            for (int i = 0; i < 10; i++) {
                Future<String> future = futures.get(i);
                if (!idDone[i] && future.isDone()) {
                    try {
                        String result = future.get();
                        System.out.printf("get result = %s\n", result);
                        counter++;
                        idDone[i] = true;
                        System.out.printf("count=%d isDown=%s\n", counter, Arrays.toString(idDone));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                }
            }


            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
