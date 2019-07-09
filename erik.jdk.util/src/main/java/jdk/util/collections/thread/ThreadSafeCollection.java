package jdk.util.collections.thread;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author erik.wang
 * @date 2019/05/08
 **/
public class ThreadSafeCollection {

    /**
     * 线程安全的集合，就是在所有方法上都添加synchronize关键字的集合；
     * 可适用于多线程环境，但是不支持"遍历时修改"。
     */
    public void test_thread_safe_collection() {

        Vector<Integer> vector = new Vector<>();

        for (int i = 0; i < 4; i++) {
            vector.add(i);
        }


        Hashtable<Integer, Integer> hashTable = new Hashtable<>();

        for (int i = 0; i < 4; i++) {
            hashTable.put(i, i);
        }


    }


    public void test_concurrent_collection() {

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        for (int i = 0; i < 10; i++) {
            copyOnWriteArrayList.add(i);
        }

        copyOnWriteArrayList.set(1, 1);
        copyOnWriteArrayList.remove(2);

        copyOnWriteArrayList.get(5);

        Iterator iterator = copyOnWriteArrayList.iterator();


    }

    public void test_block_queue() {

    }

}
