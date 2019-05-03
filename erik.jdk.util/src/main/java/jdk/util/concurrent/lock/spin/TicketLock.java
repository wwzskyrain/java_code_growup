package jdk.util.concurrent.lock.spin;

import java.util.concurrent.atomic.AtomicInteger;

public class TicketLock {
   private AtomicInteger serviceNum = new AtomicInteger(); // 服务号
   private AtomicInteger ticketNum = new AtomicInteger(); // 排队号

    /**
     *
     * @return 排队号，这个排队号将作为调用unLock函数的入参。<br/>
     * 注意:每个线程调用这个lock方法后，跟准确的说，每调用一次这个lock方法，都会"自旋"一会并跳出自旋，<br/>
     * 从而函数结束，从而调用返回，从而调用者线程获得锁。<br/>
     */
   public int lock() {
         // 首先原子性地获得一个排队号
         int myTicketNum = ticketNum.getAndIncrement();

              // 只要当前服务号不是自己的就不断轮询
       while (serviceNum.get() != myTicketNum) {
       }

       return myTicketNum;
    }

    /**
     * 释放锁。
     * @param myTicket 调用lock时的返回值。
     */
    public void unlock(int myTicket) {
        // 只有当前线程拥有者才能释放锁
        int next = myTicket + 1;
        serviceNum.compareAndSet(myTicket, next);
    }
}