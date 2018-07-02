package concurrency;//: concurrency/Joining.java
// Understanding join().

import java.util.Date;

import static net.mindview.util.Print.*;

class Sleeper extends Thread {
    private int duration;

    public Sleeper(String name, int sleepTime) {
        super(name);
        duration = sleepTime;
        start();
    }

    public void run() {
        try {
            System.out.println(getName() + "sleep!!!");
            sleep(duration);
        } catch (InterruptedException e) {
            System.out.printf("%s was interrupted. is Interrupted(): %b at time:%d\n", getName(), isInterrupted(), new Date().getTime());
            print(getName() + " was interrupted. " +
                    "isInterrupted(): " + isInterrupted());
            return;
        }
        print(getName() + " has awakened");
    }
}

class Joiner extends Thread {
    private Sleeper sleeper;

    public Joiner(String name, Sleeper sleeper) {
        super(name);
        this.sleeper = sleeper;
        start();
    }

    public void run() {
        try {
            sleeper.join(); //等待sleeper睡醒了再说。
            System.out.println(sleeper.getName() + "sleep over!");
        } catch (InterruptedException e) {
            print("Interrupted");
        }
        print(getName() + " join completed");
    }
}

public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy", 1500),
                grumpy = new Sleeper("Grumpy", 1500);
        Joiner
                dopey = new Joiner("Dopey", sleepy),
                doc = new Joiner("Doc", grumpy);

        grumpy.interrupt();
        System.out.printf("grumpy thread isInterrupted():%b at:%d \n ", grumpy.isInterrupted(), new Date().getTime());
//  wwz：从发出中断指令（调用grumpy.interrupt方法），到线程被中断并抛出InterruptedException并被捕获（指到catch代码块），是很迅速的，这之间
//  根本来不及检查线程grumpy的中断状态。（或许向可中断线程发出中断指令，根本就不会设置中断状态，从interrupt方法的api可以看出来：If none of the previous conditions hold then this thread's interrupt
//  status will be set.）
    }
} /* Output:
Grumpy was interrupted. isInterrupted(): false
Doc join completed
Sleepy has awakened
Dopey join completed
*///:~
