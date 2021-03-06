package jdk.util.proxy.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

public class DynamicProxy2 {


    public static void main(String[] args) {

        Object proxyInstance = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{Eat.class, Play.class}, new InvocationHandler() {

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//  这里没有承接"实例变量"，即目标对象targetObject，
//  所以当method表示的是"实例方法-非静态方法"时是没办法来触发method.invoke()方法的；
                System.out.println("before method invoke");

                System.out.println("the method name :" + method.getName());
//              method中有Class类型信息，所以可以以此来区分"构造代理对象时的接口数组"。
                System.out.println("afterreturning method invoke.");

                return null;
            }

        });

        System.out.println("proxyInstance 的接口类型 :" + Arrays.toString(proxyInstance.getClass().getInterfaces()));

        System.out.println("proxyInstance 的类-类型 :" + (proxyInstance.getClass()));

        if (proxyInstance instanceof Eat) {
            ((Eat) proxyInstance).eatFruit("apple");
            ((Eat) proxyInstance).eatTea("red");
        }
        if (proxyInstance instanceof Play) {
            ((Play) proxyInstance).playBall("PingPang");
            ((Play) proxyInstance).playComputerGame("HunDouLuo");
        }

        System.out.println("main over!!!");

    }


}
