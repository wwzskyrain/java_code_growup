package concurrency;//: concurrency/CallableDemo.java

import java.text.SimpleDateFormat;
import java.util.concurrent.*;
import java.util.*;

class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    public String call() {

        try {
            TimeUnit.SECONDS.sleep(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf("ready to return,taskId=%d ,time=%s.\n", id, new SimpleDateFormat("hh:mm:ss").format(new Date()));

        return "result of TaskWithResult " + id;
    }
}

public class CallableDemo {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results =
                new ArrayList<Future<String>>();
//        for (int i = 0; i < 10; i++)
//            results.add(exec.submit(new TaskWithResult(i)));

        for (int i = 10; i > 0; i--)
            results.add(exec.submit(new TaskWithResult(i)));


        System.out.printf("compute start.. %s.\n", new SimpleDateFormat("hh:mm:ss").format(new Date()));
        for (Future<String> fs : results)
//这样也是阻塞的啊。前面的任务回阻塞后面的任务，其实后面的任务已经有执行完了，可是却要等前面的任务也执行完并打印了结果后，后面的任务的执行结果才能打印出来。
            try {
                // get() blocks until completion:
                System.out.println(fs.get());
            } catch (InterruptedException e) {
                System.out.println(e);
                return;
            } catch (ExecutionException e) {
                System.out.println(e);
            } finally {
                exec.shutdown();
                System.out.printf("compute end.. %s.\n", new SimpleDateFormat("hh:mm:ss").format(new Date()));
            }
    }
} /* Output:
result of TaskWithResult 0
result of TaskWithResult 1
result of TaskWithResult 2
result of TaskWithResult 3
result of TaskWithResult 4
result of TaskWithResult 5
result of TaskWithResult 6
result of TaskWithResult 7
result of TaskWithResult 8
result of TaskWithResult 9
*///:~
