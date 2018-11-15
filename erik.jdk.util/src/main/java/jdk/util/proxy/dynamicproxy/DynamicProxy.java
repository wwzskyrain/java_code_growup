package jdk.util.proxy.dynamicproxy;

import jdk.util.proxy.staticproxy.Animal;
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

        String methodName = method.getName();

        System.out.println("proxy.getClass(): " + proxy.getClass());   //  class com.sun.proxy.$Proxy0

        Class<?> declaringClass = method.getDeclaringClass();
        System.out.println("declaringClass:" + declaringClass);

        System.out.println("");

//        System.out.println("proxy:" + proxy);     //不注释掉该句，就会陷入死循环。
//        这里会调用proxy的toString方法，而任何proxy的方法（除了getClass方法外）都会被代理，所以又进入invoke方法，从而造成无穷递归，最终造成"Exception in thread "main" java.lang.StackOverflowError".

        Object result = method.invoke(target, args);    //invoke为什么需要一个target的参数呢？
        //invoke的api可知，静态方法的执行，不许target，但是"动态方法——实例方法"，的执行就必须有个"对象"作为
        //方法的载体了，因为这时，该实例对象是有状态的。

        System.out.println("method name:    " + method.getName());

        if (declaringClass.isAssignableFrom(Animal.class) &&
                methodName.equals("doing")) {
//            return 3;     //很明显，如果这里返回数字，在强制类型转换到doing方法声明中的String时就会报错"ClassCastException"。
            return "proxy return for method Animal.method";
        }

        return null;
    }

    public static void main(String[] args) {    //这已经被我改的四不像了。

        Humen humen = new HumenImpl();

        DynamicProxy dynamicProxy = new DynamicProxy(humen);

//        HumenImpl humenDynamicProxy = (HumenImpl) Proxy.newProxyInstance(HumenImpl.class.getClassLoader(), new Class[]{Humen.class}, dynamicProxy);
//        Proxy是不能代理类的，这里会发生ClassCastException
        Object proxyInstance = Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{Humen.class, Animal.class}, dynamicProxy);
//        从参数new Class[]{Humen.class, Animal.class}可以看出，proxyInstance可以代理多个接口，但是只能有一个target代理对象。

        if (proxyInstance instanceof Humen) {
            ((Humen) proxyInstance).eat("rice");
            ((Humen) proxyInstance).drink("water");
        }

        if (proxyInstance instanceof Animal) {
            System.out.println(((Animal) proxyInstance).doing("something"));
        }
    }


}
