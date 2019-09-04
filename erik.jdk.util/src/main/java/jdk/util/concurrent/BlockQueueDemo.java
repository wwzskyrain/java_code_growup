package jdk.util.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Date 2019-08-22
 * @Created by erik
 */
public class BlockQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue(10);

        blockingQueue.add(1);
        blockingQueue.add(2);
        blockingQueue.add(3);

        blockingQueue.poll();


    }

}
