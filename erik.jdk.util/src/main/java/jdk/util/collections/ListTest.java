package jdk.util.collections;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author erik.wang
 * @date 2019/03/07
 **/
public class ListTest {


    @Test
    public void test_list_fail_fast() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                list.remove(integer);
        }
    }


    @Test
    public void test_block_queue(){

        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<String>(100);

        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();


    }






}
