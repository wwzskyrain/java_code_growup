package jdk.util.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author erik.wang
 * @date 2019/04/28
 **/
public class JoinDemo {


//  这个例子说明，join一个不是Alive的thread是不会阻塞的。详情见join的代码
//  那么当join阻塞了当前线程后，当前线程什么时候会再次唤醒？
//  答案是'After run() finishes, notify() is called by the Thread subsystem.'

    public static void main(String[] args) throws InterruptedException {

        MyThread myThread = new MyThread("a", "a");
        myThread.join();
        System.out.println("after join, before myThread start.");
        TimeUnit.SECONDS.sleep(2);
        myThread.start();
        System.out.println("over");

    }

    public static class MyThread extends Thread {

        private String content;

        public MyThread(String content, String threadName) {
            this.content = content;
            setName(threadName);
        }


        @Override
        public void run() {

            System.out.printf("%s print %s\n", Thread.currentThread().getName(), content);
        }
    }


}
