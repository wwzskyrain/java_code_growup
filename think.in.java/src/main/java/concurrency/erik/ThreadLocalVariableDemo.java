package concurrency.erik;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 */
public class ThreadLocalVariableDemo extends Thread {

    private final ThreadLocal<Integer> threadLocalIntegerVariable = new ThreadLocal<>();

    private static final Random random = new Random();


    private static final ThreadLocal<Session> threadSession = new ThreadLocal();

    public Session getSession() {
        Session s = threadSession.get();

        if (s == null) {
            s = new Session();
            threadSession.set(s);
            return s;
        } else {
            return s;
        }
    }

    @Override
    public void run() {
        int localValue = random.nextInt();
        threadLocalIntegerVariable.set(localValue);
        System.out.println("Thread: " + Thread.currentThread().getName() + " set thread local: " + localValue);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Thread: " + Thread.currentThread().getName() + " threadLocalIntegerVariable: " + threadLocalIntegerVariable.get() + ", local: " + localValue);
    }

    public static void main(String[] args) {

        test_1();
        System.out.println("test_1() over--------------");
        test_2();

    }

    /**
     * 测试：不同的线程，读写各自的"ThreadLocal变量"
     */
    public static void test_1() {
        int concurrent = 10;
        ExecutorService service = Executors.newFixedThreadPool(concurrent);
        for (int i = 0; i < concurrent; i++) {
            service.execute(new ThreadLocalVariableDemo());
        }
        service.shutdown();
    }

    /**
     * 测试：声明为成员变量的情况下，不同"对象"读写的还是同一个。
     */
    public static void test_2() {

        ThreadLocalVariableDemo demo1 = new ThreadLocalVariableDemo();
        ThreadLocalVariableDemo demo2 = new ThreadLocalVariableDemo();

        Session session1 = demo1.getSession();
        Session session2 = demo2.getSession();
        System.out.printf("session1 == session2? %b \n", session1 == session2);
    }

}