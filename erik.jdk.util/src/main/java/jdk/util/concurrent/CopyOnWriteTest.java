package jdk.util.concurrent;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * @author erik.wang
 * @date 2020-07-17 08:55
 * 1.	api操作：线程安全的List，list怎么用就怎么用，不用再担心‘遍历时不能mutative’的问题了，也就不会再抛异常-
 * 2.	使用场景：在多读少写的场景，比如--
 * 3.	底层实现：所有的mutative操作都用lock同步实现，实现思路是copy原数组，完后就对 array 重新复制，这是一个volatile变量
 * 4.	CopyOnWriteArraySet是基于CopyOnWriteArrayList完成的。
 */
public class CopyOnWriteTest {

    @Test
    public void test_copy_on_write_array_list(String[] args) {

        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();

        list.add(1);
        list.add(2);
        list.add(3);

    }

    @Test
    public void test_copy_on_write_array_set() {
        CopyOnWriteArraySet<Integer> set = new CopyOnWriteArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
    }


}
