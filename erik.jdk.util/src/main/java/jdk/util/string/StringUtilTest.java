package jdk.util.string;


import org.apache.commons.lang3.StringUtils;

public class StringUtilTest {

    public static void main(String[] args) {

        test1();

    }


    public static void test1() {

        String rightPad = StringUtils.rightPad("123", 5, '6');

        System.out.println(rightPad);


        long parseLong = Long.parseLong("001234");

        System.out.println(parseLong);
    }

}
