package com.foo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by nali on 2017/10/22.
 */
public class Slf4jTest {


    public static final Logger logger = LoggerFactory.getLogger(Slf4jTest.class);

    public static void main(String[] args) {

//        test();

        test_info_method_with_more_than_three_data();
    }

    public static void test() {
        logger.error("logger name ={} , error level", logger.getName());
        logger.info("logger name = {} ,info level", logger.getName());
    }

    public static void test_info_method_with_more_than_three_data() {


        logger.info("first:{},second:{},third:{},fours:{}", new String[]{"one", "two", "three", "four"});

    }

}
