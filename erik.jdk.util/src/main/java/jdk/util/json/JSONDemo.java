package jdk.util.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class JSONDemo {


    public static void main(String[] args) throws ParseException {

//        test1();
        test();
    }


    public static void test() throws ParseException {

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        User user = new User("erik", 26, dateFormat.parse("1990-05-16"), "emil@163.com");

        String jsonString = JSON.toJSONString(user);

        System.out.println(jsonString);

        User user1 = JSON.parseObject(jsonString, User.class);

        System.out.println(user1);

        JSONObject jsonObject = JSON.parseObject(jsonString);

        Integer age = jsonObject.getInteger("age");

        System.out.println(age);

    }

    public static void test1() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        List<User> userList = new ArrayList<>();

        userList.add(new User("erik", 26, dateFormat.parse("1990-05-16"), "emil@163.com"));

        userList.add(new User("fran", 33, dateFormat.parse("1985-04-09"), "emil@163.com"));

        String usersJsonString = JSON.toJSONString(userList);

        System.out.println(usersJsonString);

        List<User> userList1 = JSON.parseObject(usersJsonString, new TypeReference<List<User>>() {
        });

        System.out.println(userList1);


    }

}
