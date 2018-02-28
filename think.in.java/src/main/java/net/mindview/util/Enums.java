//: net/mindview/util/Enums.java
package net.mindview.util;

import enumerated.menu.Course;

import java.util.Random;

public class Enums {
    private static Random rand = new Random(47);

    public static <T extends Enum<T>> T random(Class<T> ec) {
        return random(ec.getEnumConstants());
    }

    public static <T> T random(T[] values) {
        return values[rand.nextInt(values.length)];
    }

    public static void main(String[] args) {


        Enums enums=new Enums();
        for(int i=0;i<Course.values().length;i++)
        {
            System.out.println(enums.random(Course.class).getClass());
        }


    }
} ///:~
