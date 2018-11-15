package erik.java8;

import erik.java8.model.Apple;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Created by nali on 2017/9/24.
 */
public class LambdaExpression {


    public static void main(String[] args) {


        List<Apple> apples = Arrays.asList(new Apple(3), new Apple(5), new Apple(1));

        Comparator<Apple> appleComparatorByWeight = new Comparator<Apple>() {
            @Override
            public int compare(Apple o1, Apple o2) {
                return o1.getWeight() - o2.getWeight();
            }
        };

        List<String> words = Arrays.asList("ccc", "bbb", "zzz", "ddd");
        words.sort((word1, word2) -> word1.compareTo(word2));
        words.sort(String::compareTo);

        apples.stream().sorted((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());
//      lambda表达式
        Function<String, Integer> function = (String word) -> Integer.parseInt(word);
//      方法引用
        Function<String, Integer> function1 = String::length;


    }


    public static void lambda() {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");

        Collections.sort(names, Comparator.naturalOrder());

        System.out.println(names);

    }


}
