package erik.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by nali on 2017/9/24.
 */
public class StreamMain {


    public static void main(String[] args) {

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");


        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        System.out.println("--------------");
        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);   // "aaa1", "aaa2"

        stringCollection
                .stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);

        System.out.println("--------------");

        boolean anyStartsWithA =
                stringCollection
                        .stream()
                        .anyMatch((s) -> s.startsWith("a"));

        System.out.println(anyStartsWithA);      // true

        boolean allStartsWithA =
                stringCollection
                        .stream()
                        .allMatch((s) -> s.startsWith("a"));

        System.out.println(allStartsWithA);      // false

        boolean noneStartsWithZ =
                stringCollection
                        .stream()
                        .noneMatch((s) -> s.startsWith("z"));

        System.out.println(noneStartsWithZ);      // true

        System.out.println("----------------------------");

        List<String> collect = stringCollection.stream()
                .filter((s) -> s.startsWith("a"))
                .collect(Collectors.toList());

        System.out.println(collect);

    }


}
