package jdk.util.collections;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {

    public static void main(String[] args) {

        Map<String ,String > hashMap= new HashMap<>();

        hashMap.put("1","one");
        hashMap.put("2","two");

        Long key = 2l;
        String two = hashMap.get(key.toString());

        System.out.println(two);

    }

}
