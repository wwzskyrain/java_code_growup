package jdk.util.concurrent.lock.free;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @Date 2019-08-19
 * @Created by erik
 */
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();
        queue.offer(1);
        Integer value = queue.poll();


    }

}
