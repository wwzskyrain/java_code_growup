package com.erik.jdk;

import java.math.BigDecimal;

/**
 * Created by nali on 2017/8/29.
 */
public class UsageBigDecimal {

    public static void main(String[] args) {
        BigDecimal decimal = new BigDecimal(1234.3);

        System.out.println(decimal.intValue());


        BigDecimal rate = new BigDecimal("1.445");
        System.out.println("before divide:"+rate);


        System.out.println(rate.setScale(2,BigDecimal.ROUND_HALF_UP));
        System.out.println(rate.setScale(2,BigDecimal.ROUND_HALF_DOWN));

        System.out.println(new BigDecimal("1.499").setScale(0, BigDecimal.ROUND_HALF_UP));

        BigDecimal amount=new BigDecimal("6.0000");
//        List<BigDecimal> bigDecimalList=new ArrayList<>();

//        bigDecimalList.add(amount);
//        bigDecimalList.add(new BigDecimal("0.1000"));

//        bigDecimalList.forEach(bigDecimal -> {
//
//            if(bigDecimal.compareTo(new BigDecimal("1"))==-1){
//
//                System.out.println(bigDecimal.setScale(1));
//            }else{
//                System.out.println(bigDecimal.setScale(0));
//            }
//
//        });



    }

}
