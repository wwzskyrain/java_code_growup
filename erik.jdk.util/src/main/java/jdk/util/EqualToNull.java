package jdk.util;

import jdk.util.vo.Student;

public class EqualToNull {

    public static void main(String[] args) {


        Student student =new Student();


        if(6==student.getAge()){
            System.out.println(true);
        }else {
            System.out.println(false);
        }

    }




}
