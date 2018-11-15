package jdk.util.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.Arrays;

public class CGLibProxy2 {  //CGLibProxy的另一种写法

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();

        enhancer.setSuperclass(HumenImpl.class);

        enhancer.setCallback(new MethodInterceptor() {

            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {

                System.out.println("before .. ");

                System.out.println("被代理的方法的名字："+method.getName());

                Object result = proxy.invokeSuper(obj, args);

                System.out.println("afterreturning .. ");

                return result;
            }

        });

        Object proxyInstance = enhancer.create();

        System.out.println("代理对象的类型：" + proxyInstance.getClass());
        System.out.println("代理对象的类型的父类型"+proxyInstance.getClass().getSuperclass());
        System.out.println("代理对象的接口类型：" + Arrays.toString(proxyInstance.getClass().getInterfaces()));

        if (proxyInstance instanceof HumenImpl) {

            HumenImpl humenProxy = (HumenImpl) proxyInstance;

            humenProxy.eat("rice");
            humenProxy.additionalMethod();
            System.out.println(humenProxy.toString());

        }


    }

}
