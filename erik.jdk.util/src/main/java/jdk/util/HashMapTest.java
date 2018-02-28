package jdk.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by nali on 2017/10/19.
 */
public class HashMapTest {

    public static void main(String[] args) {

        Map<Integer, Integer> map = new HashMap<>();

        map.put(100, 100);
        map.put(200, 200);
        map.put(300, 300);
        map.put(400, 400);


        Stream<Map.Entry<Integer, Integer>> largerThan150 = map.entrySet().stream().filter(integerIntegerEntry -> (integerIntegerEntry.getKey() > 150));

        map.forEach((key,value)->key=key+value);

        System.out.println(map.toString());

        System.out.println(Arrays.toString(largerThan150.toArray()));
    }

}
