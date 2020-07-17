package jdk.util.concurrent.lock.spin;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;


/**
 * @author 1.链式的；每一个Node被一个线程持有；每一个线程持有的node是它的前驱线程的。
 * 2.持有者一直在循环等待着前驱node从lock状态变成unlock状态，然后立刻跳出循环，从而获得锁。
 */
public class ClhLock {
    public static class CLHNode {
        private volatile boolean isLocked = true; // 默认是在等待锁
    }

    @SuppressWarnings("unused")
    private volatile CLHNode tail;
    private static final AtomicReferenceFieldUpdater<ClhLock, CLHNode> UPDATER = AtomicReferenceFieldUpdater
            .newUpdater(ClhLock.class, CLHNode.class, "tail");

    public void lock(CLHNode currentThread) {
        CLHNode preNode = UPDATER.getAndSet(this, currentThread);
        if (preNode != null) {
            //已有线程占用了锁，进入自旋
            while (preNode.isLocked) {
            }
        }
    }

    public void unlock(CLHNode currentThread) {

        // 如果队列里只有当前线程，则释放对当前线程的引用（for GC）。
        if (!UPDATER.compareAndSet(this, currentThread, null)) {
            // 还有后续线程
            // 改变状态，让后续线程结束自旋
            currentThread.isLocked = false;
        } else {
            currentThread = null;
        }
    }

    public static void main(String[] args) {

        ClhLock lock = new ClhLock();

        CLHNode clhNode = new CLHNode();

        try {
            lock.lock(clhNode);
            // TODO: 2020-07-08


        } finally {
            lock.unlock(clhNode);
        }


    }


}