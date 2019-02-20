package erik.java8.stream;

import erik.java8.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * Created by nali on 2017/9/26.
 */
public class StreamMain2 {


    public static void main(String[] args) {

        test2();

    }


    public static  void  test1(){

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream().map(s -> s.substring(0, 3)).distinct().forEach(System.out::println);

        stringCollection.stream().map(s -> s.substring(0, 3)).distinct().collect(toList());
    }

    public static void test2(){

        List<Student> students = new ArrayList<>();
        students.add(new Student(1L,"songjiang",12.0f));
        students.add(new Student(2L,"WuYong",12.0f));
        students.add(new Student(3L,"LinChong",12.0f));

        List<Long> studentIds = students.stream().map(student -> student.getId()).collect(Collectors.toList());

        System.out.println(studentIds);

    }

}
