package jdk.util.time;

import java.util.Calendar;
import java.util.Date;

public class TimeTest {


    public static void main(String[] args) {

        Calendar calendar=Calendar.getInstance();

        calendar.set(2017,10,14,13,0,0);

        System.out.println("startTime:"+calendar.getTime().getTime());
        System.out.println("endTime:"+new Date().getTime());

    }

}
