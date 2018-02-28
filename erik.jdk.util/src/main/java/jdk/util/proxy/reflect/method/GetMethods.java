package jdk.util.proxy.reflect.method;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GetMethods {


    public static void main(String[] args) {

        try {
            Class studentClass = Class.forName("jdk.util.proxy.reflect.method.Student");

            System.out.println("***************获取所有的”公有“方法*******************");

            Method[] studentClassMethods = studentClass.getMethods();
            for (Method method : studentClassMethods) {
                System.out.println(method);
            }

            System.out.println("***************获取所有的方法，包括私有的*******************");
            Method[] studentClassDeclaredMethods = studentClass.getDeclaredMethods();
            for (Method method : studentClassDeclaredMethods) {
                System.out.println(method);
            }

            System.out.println("***************获取公有的show1()方法*******************");
            try {
                Method methodShow1 = studentClass.getMethod("show1", String.class);

                System.out.println(methodShow1);

                Constructor constructor = studentClass.getConstructor();

                Object instance = constructor.newInstance();

                methodShow1.invoke(instance, "show1-parameter-刘德华");

            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }


            System.out.println("***************获取私有的show4()方法******************");
            try {
                Method methodShow4 = studentClass.getDeclaredMethod("show4", int.class);

                try {
                    try {
                        methodShow4.setAccessible(true);
                        methodShow4.invoke(studentClass.newInstance(), 27);
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }




    }

}
