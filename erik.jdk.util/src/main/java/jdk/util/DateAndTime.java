package jdk.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by nali on 2017/9/27.
 */
public class DateAndTime {

    public static void main(String[] args) {

        test();

    }

    public static void test() {

        Calendar calendar = Calendar.getInstance();

        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat simpleDateFormat1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        calendar.set(2017,5,27,0,0,0);

        for(int hour=0;hour<24;hour++){

            calendar.add(Calendar.HOUR_OF_DAY,1);
            System.out.println(simpleDateFormat.format(calendar.getTime()));
            System.out.println(simpleDateFormat1.format(calendar.getTime()));

        }

    }

}
