package concurrency.erik;


import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;


public class InterruptTest {


    public static void main(String[] args) {

        Thread ioThread = new Thread() {

            @Override
            public void run() {
                print("Waiting for read():");
                try {
                    System.in.read();
                } catch (IOException e) {
//                  wwz：通过Thread.interrupter方法，是不能中断"IO"阻塞的。
                    System.out.printf("%d isInterrupted():%b at time:%d\n", getName(), isInterrupted(), new Date().getTime());
//                  wwz：意图判断线程是"interrupted"状态，就不应该在该线程内的（在外面）。所以，上面这行代码要是能执行，也只能是"non-interrupted"状态？
//                  wwz：异常块中的代码还是在原线程中运行吗？应该是的，这也就解释了，为什么catch一旦捕捉了InterruptedException后，就将"中断位"清楚。
                    e.printStackTrace();
                }
            }

        };

        ioThread.start();

        ioThread.interrupt();
        System.out.printf("main space:isInterrupted:%b at time:%d\n", ioThread.isInterrupted(), new Date().getTime());

        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            System.in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
