package jdk.util.collections;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author erik.wang
 * @date 2019/05/10
 **/
public class MapTest {

    @Test
    public void test_tree_map() {


        TreeMap<String, String> treeMap = new TreeMap<>();


    }

    @Test
    public void test_hash_map() {
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 14; i++) {
            map.put(i, String.format("value-%d", i));
        }
        System.out.println(map.keySet().contains(100));
        System.out.println((1 << (16)) - 1);
    }

    @Test
    public void test_concurrent_hash_map() {

    }

}
