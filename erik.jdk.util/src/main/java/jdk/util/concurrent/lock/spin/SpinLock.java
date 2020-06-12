package jdk.util.concurrent.lock.spin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author erik.wang
 * @date 2019/05/03
 *
 * 1.简单的自旋，每次都是大家伙一块竞争，非公平(非先到先得)
 *
 **/
public class SpinLock {

    private static final Logger logger = LoggerFactory.getLogger(SpinLock.class);

    private AtomicReference<Thread> owner = new AtomicReference<Thread>();

    public void lock() {
        Thread currentThread = Thread.currentThread();

        // 如果锁未被占用，则设置当前线程为锁的拥有者
        while (!owner.compareAndSet(null, currentThread)) {
        }
    }

    public void unlock() {
        Thread currentThread = Thread.currentThread();

        // 只有锁的拥有者才能释放锁
        owner.compareAndSet(currentThread, null);
    }


    public static class SpinLockTestThread extends Thread {

        public SpinLock spinLock;

        public SpinLockTestThread(SpinLock spinLock) {
            this.spinLock = spinLock;
        }

        @Override
        public void run() {

            spinLock.lock();
            logger.info("{} get the spinLock", getName());

            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            logger.info("{} will unlock", getName());
            spinLock.unlock();

        }
    }

    public static void main(String[] args) {

        SpinLock spinLock = new SpinLock();
        for (int i = 0; i < 10; i++) {
            new SpinLockTestThread(spinLock).start();
        }

    }
}