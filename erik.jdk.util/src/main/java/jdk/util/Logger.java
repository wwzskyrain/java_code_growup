package jdk.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Date 2019-08-19
 * @Created by erik
 */
public class Logger {

    public static void log(String pattern, String... parameters) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String timeStamp = dateFormat.format(new Date());
        String threadName = Thread.currentThread().getName();

        String logContent = pattern;
        for (String parameter : parameters) {
            logContent = logContent.replaceFirst("\\{}", parameter);
        }

        System.out.printf("%s 【%s】 %s\n", timeStamp, threadName, logContent);
    }

    public static void main(String[] args) {
        log("hello {} and {}", "erik.one", "erik.two","erik.three");
    }


}
