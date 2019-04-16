package erik.java8.stream;

import erik.java8.Person;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author erik.wang  @date 2019/02/16
 **/
public class ReduceDemo {

    public static List<Integer> integersData = Arrays.asList(1, 2, 3, 4, 5, 6);

    public static void reduceDemo() {

        Integer sum = integersData.stream().reduce(0, Integer::sum);
        System.out.println("sum:" + sum);
//      累积和累加同理；

        Integer max = integersData.stream().reduce(0, Integer::max);
        System.out.println("max:" + max);
//      最大值和最小是同理；

        Integer counter = integersData.stream().map(num -> 1).reduce(0, Integer::sum);
        System.out.println("count:" + counter);

        String stringConcat = Person.getPersons().stream().map(Person::getFirstName).distinct().sorted().collect(Collectors.joining());
        System.out.println("stringConcat：" + stringConcat);
    }

    public static void main(String[] args) {
//        counter();
//        reduceDemo();
        test_average();
    }

    public static void test_average() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
//        System.out.println(list.stream().mapToDouble(Double::valueOf).average().getAsDouble());
//
//        System.out.println(list.stream().collect(Collectors.averagingDouble(Double::valueOf)));

        list.stream().reduce(0, (a, b) -> a + b);

    }

}
