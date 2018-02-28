package jdk.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nali on 2017/10/19.
 */
public class BigDecimalTest {

    public static void main(String[] args) {

        test1();
    }

    public static void test2(){

        List<BigDecimal> bigDecimalList = new ArrayList<BigDecimal>();


//        bigDecimalList.add(main.scaldThree(new BigDecimal("0.01")));
//
//        bigDecimalList.add(main.scaldThree(new BigDecimal("0.02")));

        bigDecimalList.add(new BigDecimal("0.1"));

        bigDecimalList.add(new BigDecimal("0.2"));

//        bigDecimalList.add(main.scaldThree(new BigDecimal("0.0004")));
//        bigDecimalList.add(main.scaldThree(new BigDecimal("0.0005")));


        bigDecimalList.add(scaldThree(new BigDecimal("0.03450")));
        bigDecimalList.add(scaldThree(new BigDecimal("0.030")));

        bigDecimalList.forEach(bigDecimal -> System.out.println(bigDecimal + " scale=" + bigDecimal.scale() + " precision=" + bigDecimal.precision()));

    }

    public static BigDecimal scaldThree(BigDecimal bigDecimal) {

        bigDecimal=bigDecimal.stripTrailingZeros();
        if (bigDecimal.scale() > 3) {

            bigDecimal = bigDecimal.setScale(3, BigDecimal.ROUND_HALF_UP);

        }

        return bigDecimal;
    }


    public static void test(){

        BigDecimal bigDecimal=new BigDecimal("0.2");
        bigDecimal=bigDecimal.setScale(3);
        System.out.printf("bigDecimal=%s,scale=%d,precision=%d",bigDecimal,bigDecimal.scale(),bigDecimal.precision());

    }

    public static void test1(){

        BigDecimal one = new BigDecimal("2.3");
        System.out.println(one.compareTo(new BigDecimal("0")));

    }

}
