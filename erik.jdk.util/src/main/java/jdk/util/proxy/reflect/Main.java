package jdk.util.proxy.reflect;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        Person person = new Person();


        System.out.println(person.getClass());

        System.out.println(person.getClass().getClass());

        System.out.println(person.getClass().getClassLoader());

        System.out.println(Arrays.toString(person.getClass().getInterfaces()));

        System.out.println(Behave.class);

    }

}
