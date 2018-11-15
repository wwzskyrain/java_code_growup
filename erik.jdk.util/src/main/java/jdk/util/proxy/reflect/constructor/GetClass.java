package jdk.util.proxy.reflect.constructor;

public class GetClass {

    public static void main(String[] args) {
        //第一种方式获取Class对象
        GetClass stu1 = new GetClass();//这一new 产生一个Student对象，一个Class对象。
        Class stuClass = stu1.getClass();//获取Class对象
        System.out.println(stuClass.getName());

        //第二种方式获取Class对象
        Class stuClass2 = GetClass.class;
        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个

        //第三种方式获取Class对象
        try {
            //注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
            Class stuClass3 = Class.forName("jdk.util.proxy.reflect.constructor.GetClass");
            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
