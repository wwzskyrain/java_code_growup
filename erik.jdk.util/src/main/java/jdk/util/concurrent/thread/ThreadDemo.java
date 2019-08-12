package jdk.util.concurrent.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @Date 2019-07-30
 * @Created by erik
 */
public class ThreadDemo {

    public static void main(String[] args) {

        Callable<String> getNowTimeStamp = new Callable<String>() {

            @Override
            public String call() throws Exception {
                System.out.println("enter getNowTimeStamp callable!");
                TimeUnit.SECONDS.sleep(1);
                return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            }
        };

        FutureTask<String> futureTask = new FutureTask(getNowTimeStamp);

        Thread getNowTimeStampThread = new Thread(futureTask);

        getNowTimeStampThread.start();

        try {
            String nowTimeStamp = futureTask.get();
            System.out.println("nowTimeStamp:" + nowTimeStamp);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        Executors.newSingleThreadExecutor();


    }


}
