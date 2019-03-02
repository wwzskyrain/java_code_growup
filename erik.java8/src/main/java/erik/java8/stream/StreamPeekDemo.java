package erik.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author erik.wang
 * @date 2019/03/02
 **/
public class StreamPeekDemo {

    public static void main(String[] args) {

//  peek就是消费一把同时有不影响Stream。
        List<String> data = Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        System.out.println(Arrays.toString(data.toArray()));
    }

}
