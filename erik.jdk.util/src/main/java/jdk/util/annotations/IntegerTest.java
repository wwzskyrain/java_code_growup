package jdk.util.annotations;

import java.util.Calendar;
import java.util.Date;

/**
 * @author erik.wang
 * @date 2019/03/06
 **/
public class IntegerTest {

    private final static Long BASE_MINUTE_FOR_2016 = 24192960L;


    public static void main(String[] args) {

        Long nowMillisecond = new Date().getTime();

        Long nowMinute = nowMillisecond / 1000 / 60;

        long promoCode = nowMinute - BASE_MINUTE_FOR_2016;

        int minutesForOneYear = 365 * 24 * 60;

        System.out.println(promoCode);

        System.out.println(Integer.valueOf(Long.toString(promoCode)));
        System.out.println(minutesForOneYear);

    }


}
