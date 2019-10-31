package erik.java8.reduce;

import erik.java8.Person;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author erik.wang
 * @Date 2019-10-20
 */
public class ReduceDemo {

    public static void main(String[] args) {

        List<Person> persons = Person.getPersons();
        System.out.println(persons);
        System.out.println(persons.stream().collect(Collectors.summingInt(Person::getAge)));
        System.out.println(persons.stream().collect(Collectors.summingDouble(Person::getAge)));

    }

}
