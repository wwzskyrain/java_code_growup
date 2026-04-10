package jdk.util.concurrent.thread;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// 三个线程，A负责顺序打印大写字母，B一个负责顺序打印小写字母，C一个负责顺序打印1-26，而且要求他们，ABC没轮次都顺序打印。

public class ThreeThreadPrint {

//   方法1就使用锁+状态标志来完成；锁有两个版本，一个是synchronize，一个是显示锁Lock；

    public int flag = 0; // 0,1,2,0,1,2,...,
    Lock lock = new ReentrantLock();
    Condition upperCond = lock.newCondition();
    Condition lowerCond = lock.newCondition();
    Condition numCond = lock.newCondition();

    public void printUpper() {
        for (int i = 0; i < 26; i++) {
            lock.lock();
            try {
                while (flag != 0) {
                    //  不为0我不干活，upperCond你去java的并发机制把我挂起来。
                    // 一会有人喊醒你，你再喊我。我在看看是不是该我干活。
                    upperCond.await();
                }
                // 开始干活
                System.out.print((char) ('A' + i));
                flag = 1;
                lowerCond.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printLower() {
        for (int i = 0; i < 26; i++) {
            lock.lock();
            try {
                while (flag != 1) {
                    //  不为0我不干活，upperCond你去java的并发机制把我挂起来。
                    // 一会有人喊醒你，你再喊我。我在看看是不是该我干活。
                    lowerCond.await();
                }
                // 开始干活
                System.out.print((char) ('a' + i));
                flag = 2;
                numCond.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                lock.unlock();
            }
        }
    }

    public void printNum() {
        for (int i = 0; i < 26; i++) {
            lock.lock();
            try {
                while (flag != 2) {
                    //  不为0我不干活，upperCond你去java的并发机制把我挂起来。
                    // 一会有人喊醒你，你再喊我。我在看看是不是该我干活。
                    numCond.await();
                }
                // 开始干活
                System.out.println(i);
                flag = 0;
                upperCond.signalAll();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            } finally {
                lock.unlock();
            }
        }
    }

    public void PrintWithLock() {
        new Thread(this::printUpper, "print_upper").start();
        new Thread(this::printLower, "print_lower").start();
        new Thread(this::printNum, "print_num").start();
    }

    public static void main(String[] args) {
        new ThreeThreadPrint().PrintWithLock();
    }

}
