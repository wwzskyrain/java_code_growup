package jdk.util.concurrent.thread;

/**
 * @Date 2019-08-02
 * @Created by erik
 */
public class ThreadExceptionDemo {


    public static void main(String[] args) {

        new Thread(() -> {
            throw new RuntimeException(Thread.currentThread() + "exception from subThread.");
        }).start();
        System.out.println(Thread.currentThread() + "main thread over.");

    }

}
