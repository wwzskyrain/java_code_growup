package concurrency;//: concurrency/CloseResource.java
// Interrupting a blocked task by
// closing the underlying resource.
// {RunByHand}

import java.net.*;
import java.util.concurrent.*;
import java.io.*;

import static net.mindview.util.Print.*;

public class CloseResource {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        ServerSocket server = new ServerSocket(8080);
        InputStream socketInput =
                new Socket("localhost", 8080).getInputStream();
        exec.execute(new IOBlocked(socketInput));
        exec.execute(new IOBlocked(System.in));

//        Future<?> future = exec.submit(new IOBlocked(socketInput));
//        future.cancel(true);


        TimeUnit.SECONDS.sleep(1);
        print("Shutting down all threads");
//        exec.shutdownNow(); //会调用Thread.interrupt方法来"设置中断位"。
        exec.shutdown(); //不会调用Thread.interrupt方法来"设置中断位"。
        TimeUnit.SECONDS.sleep(1);

        //wwz：在关闭底层资源之前，应该先检查下线程状态吧。

//        boolean ioBlockedThreadIsCanceled = future.isCancelled();
//        if (ioBlockedThreadIsCanceled) {
//            System.out.printf("future %s is cancel \n",future.toString());
//
//        }
        print("Closing " + System.in.getClass().getName());
        System.in.close(); // Releases blocked thread

        TimeUnit.SECONDS.sleep(5);

        print("Closing " + socketInput.getClass().getName());
        socketInput.close(); // Releases blocked thread
//      关闭资源，如in.close，会引发in.read（）抛出错误么？System.in.close会，socketInput.close不会？


    }
} /* Output: (85% match)
Waiting for read():
Waiting for read():
Shutting down all threads
Closing java.net.SocketInputStream
Interrupted from blocked I/O
Exiting IOBlocked.run()
Closing java.io.BufferedInputStream
Exiting IOBlocked.run()
*///:~
