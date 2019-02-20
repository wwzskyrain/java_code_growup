package erik.java8.stream;

import erik.java8.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by nali on 2017/10/27.
 */
public class StreamMain3 {

    public static void main(String[] args) {
        test();
    }

    public static void test(){

        List<Student> students=new ArrayList<>(Student.students);

        long count = students.stream().filter(student -> student.getHeigth() > 3.0f).count();
        System.out.println(count);

        Stream<Student> studentStream = students.stream().filter(student -> student.getHeigth() > 3.0f);

        Optional<Student> firstStudentOption = studentStream.findFirst();

        Student firstStudent = firstStudentOption.get();
        System.out.println(students.stream().filter(student -> student.getHeigth() > 3.0f).findFirst().get());



    }

}
