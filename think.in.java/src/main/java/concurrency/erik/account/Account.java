package concurrency.erik.account;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author erik.wang
 * @date 2019/03/30
 **/
public class Account {


    private int balance;
    private final Lock lock = new ReentrantLock();


    /**
     * 这是极客时间中的专栏-Java并发编程实战-中的一个例子，转账的例子；我觉得这里有几个问题值得思考
     * 1.锁，不管是隐士锁还是显示锁，都是要对加减锁之间的代码块的保护。因为这些代码不能被打断，而必须只能被一个线程执行完毕，而另外的线程则必须等待。
     * 2.为什么会有这种冲突，即加锁是干什么：很好解释，可以按照转账例子来解释。
     * 3.冲突了就有胜有负，胜者进入锁取余，败者就只能等待进入锁区域的胜者从锁区域出来；败者这是的状态是阻塞状态。
     * 4.当胜者从锁区域中出来后，败者怎么被唤醒呢？这里就迁出了"同步"的问题了。所以说"互斥"和"同步"是一阴一阳，一因一果。
     * 5.
     * @param tar
     * @param amt
     */
    void transfer(Account tar, int amt) {
        while (true) {
            if (this.lock.tryLock()) {
                try {
                    if (tar.lock.tryLock()) {
                        try {
                            this.balance -= amt;
                            tar.balance += amt;
                        } finally {
                            tar.lock.unlock();
                        }
                    }//if
                } finally {
                    this.lock.unlock();
                }
            }//if
        }//while
    }//transfer
}



