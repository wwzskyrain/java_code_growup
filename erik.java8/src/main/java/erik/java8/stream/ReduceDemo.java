package erik.java8.stream;

import erik.java8.Person;

import java.util.Arrays;
import java.util.List;
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
        System.out.println("stringConcat："+stringConcat);
    }

    public static void main(String[] args) {
//        counter();
        reduceDemo();
    }

    public static void collect(){
    }

}
