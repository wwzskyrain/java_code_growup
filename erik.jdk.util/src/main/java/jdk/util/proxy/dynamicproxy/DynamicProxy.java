package jdk.util.proxy.dynamicproxy;

import jdk.util.proxy.staticproxy.Humen;
import jdk.util.proxy.staticproxy.HumenImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//  proxy 是代理对象本身,就是this。

        System.out.println("before ");

//        System.out.println(proxy.getClass());   //  class com.sun.proxy.$Proxy0

//        System.out.println("proxy:" + proxy);     //不注释掉该句，就会陷入死循环。
//        这里会调用proxy的toString方法，而任何proxy的方法（除了getClass方法外）都会被代理，所以又进入invoke方法，从而造成无穷递归，最终造成"Exception in thread "main" java.lang.StackOverflowError".

//        Object result = method.invoke(target, args);

        System.out.println("method name:    "+method.getName());

        System.out.println("after ");

        return null;
    }

    public static void main(String[] args) {    //这已经被我改的四不像了。

        Humen humen = new HumenImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(humen);

//        HumenImpl humenDynamicProxy = (HumenImpl) Proxy.newProxyInstance(HumenImpl.class.getClassLoader(), new Class[]{Humen.class}, dynamicProxy);
//        Proxy是不能代理类的，这里会发生ClassCastException
        Object proxyInstance = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{Humen.class}, dynamicProxy);

        if(proxyInstance instanceof Humen){
            ((Humen)proxyInstance).eat("rice");
        }


    }


}
