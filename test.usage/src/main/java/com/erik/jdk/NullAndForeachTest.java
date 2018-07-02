package com.erik.jdk;

import java.util.ArrayList;
import java.util.List;

public class NullAndForeachTest {

    public static void main(String[] args) {

        List<String> array = null;

        for (String string:array) {

            System.out.println(string);

        }



    }

}
