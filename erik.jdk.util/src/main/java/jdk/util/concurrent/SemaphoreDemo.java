package jdk.util.concurrent;

import java.util.concurrent.Semaphore;

/**
 * @Date 2019-08-19
 * @Created by erik
 */
public class SemaphoreDemo {

    public static void main(String[] args) {
        //new Semaphore(permits,fair):初始化许可证数量和是否公平模式的构造函数
        Semaphore semaphore = new Semaphore(5, true);

        //---此线程会一直阻塞，直到获取这个许可证，或者被中断(抛出InterruptedException异常)。
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
