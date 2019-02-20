package erik.java8;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nali on 2017/9/24.
 */
public class Person {

    String firstName;
    String lastName;

    Integer age;
    Boolean sex;

    Person() {
    }

    Person(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String firstName, String lastName, Integer age, Boolean sex) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public static List<Person> getPersons() {

        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            personList.add(new Person(
                    String.format("firstName%d", i / 2),
                    String.format("lastName%d", i),
                    i,
                    i % 2 == 0));
        }

        return personList;
    }

}
