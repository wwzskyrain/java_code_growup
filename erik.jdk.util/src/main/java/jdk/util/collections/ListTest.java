package jdk.util.collections;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author erik.wang
 * @date 2019/03/07
 **/
public class ListTest {


    public static void main(String[] args) {

        test_list_fail_fast();

    }


    public static void test_list_fail_fast() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(2);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer integer = iterator.next();
            if (integer == 2)
                list.remove(integer);
        }
    }


}
