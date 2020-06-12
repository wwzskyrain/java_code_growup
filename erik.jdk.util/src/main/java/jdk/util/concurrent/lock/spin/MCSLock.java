package jdk.util.concurrent.lock.spin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 锁的名字的由来：根据其发明人来命名，发明人是John Mellor-Crummey和Michael Scott
 * 1.不用cas，cas指令本身也很贵重的
 * 2.等在一个volatile-布尔变量上
 */
public class MCSLock {

    private static final Logger logger = LoggerFactory.getLogger(MCSLock.class);

    public static class MCSNode {
        volatile MCSNode next;
        volatile boolean isBlock = true; // 默认是在等待锁
    }

    /**
     * 指向最后一个申请锁的MCSNode
     */
    volatile MCSNode tail;

    private static final AtomicReferenceFieldUpdater UPDATER = AtomicReferenceFieldUpdater
            .newUpdater(MCSLock.class, MCSNode.class, "tail");

    public void lock(MCSNode currentThreadNode) {

        MCSNode predecessor = (MCSNode) UPDATER.getAndSet(this, currentThreadNode);// step 1
        if (predecessor != null) {
            predecessor.next = currentThreadNode;// step 2

            while (currentThreadNode.isBlock) {// step 3 : 自旋处
            }
        } else { // 只有一个线程在使用锁，没有前驱来通知它，所以得自己标记自己为非阻塞
            currentThreadNode.isBlock = false;
        }
        logger.info("some guy acquire lock.");

    }

    public void unlock(MCSNode currentThreadNode) {
        if (currentThreadNode.isBlock) {
            // 锁拥有者进行释放锁才有意义
            return;
        }

        if (currentThreadNode.next == null) {
            // 检查是否有人排在自己后面
            if (UPDATER.compareAndSet(this, currentThreadNode, null)) {
                // step 4
                // compareAndSet返回true表示确实没有人排在自己后面
                return;
            } else {
                // 突然有人排在自己后面了，可能还不知道是谁，下面是等待后续者
                // 这里之所以要忙等是因为：step 1执行完后，step 2可能还没执行完
                while (currentThreadNode.next == null) { // step 5
                }
            }
        }

        currentThreadNode.next.isBlock = false;
        currentThreadNode.next = null;// for GC
        logger.info("some guy unlock.");
    }

    public static void main(String[] args) throws InterruptedException {

        MCSLock mcsLock = new MCSLock();
        for (int i = 0; i < 6; i++) {
            new McsLockThread(mcsLock).start();
        }

        TimeUnit.SECONDS.sleep(60);

    }

}