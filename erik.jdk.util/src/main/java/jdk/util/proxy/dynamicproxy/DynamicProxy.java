package jdk.util.proxy.dynamicproxy;

import jdk.util.proxy.staticproxy.HumanImpl;
import jdk.util.proxy.staticproxy.Humen;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy implements InvocationHandler {

    private Object target;

    public DynamicProxy(Object target) {
        this.target = target;
    }

    /**
     * @param proxy 代理对象，不是代理逻辑体，即不是this
     * @param method 当前被调用的方法
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {


        Class<?> declaringClass = method.getDeclaringClass();

        System.out.printf("proxy-> method name:%s with declaringClass:%s\n", method.getName(), declaringClass);

//        System.out.println("proxy:" + proxy);     //不注释掉该句，就会陷入死循环。

        Object result = method.invoke(target, args);
        //invoke为什么需要一个target的参数呢？
        //invoke的api可知，静态方法的执行，不许target，但是"动态方法——实例方法"的执行就必须有个"对象"作为
        //方法的首体了，因为这时，该实例对象是有状态的。

        return result;
    }

    public static void main(String[] args) {

        test_dynamic_proxy_base();
//        test_dynamic_proxy_invoke_directly_proxy_logic();
    }

    /**
     * 1.动态代理只能代理实现了接口的类，而且只能代理其接口中的方法
     * 2.DynamicProxy只是不是一个代理容器，而是代理逻辑体。
     * 3.originalHuman是"源对象"，即"被代理对象"；
     * 4.dynamicProxy是"代理逻辑"
     * 5.通过Proxy.newProxyInstance生成的是"代理对象"；
     * 6.调用源对象是不会触发代理逻辑的，调用"代理对象"才会触发代理逻辑
     * 7.代理逻辑就是DynamicProxy的invoke方法。能否直接调用dynamicProxy代理逻辑实体的该invoke方法？能，{@link DynamicProxy#test_dynamic_proxy_invoke_directly_proxy_logic}
     * 8.使用方式：有了代理对象，以后就那这代理对象用就好了；这种使用方式跟静态代理——手动编写代理类也没有高明多少。
     * 9.注意点：代理逻辑中的target一定要是"被代理对象"，代理的玩法是"增强"被代理对象的功能，而不是替换它，所以它一定不能为空。
     * 10.总结来说：代理有三要素：被代理对象，代理，代理逻辑。客户端对代理的调用，会有jdk代理机制传递到代理逻辑处，代理逻辑又两部分组成：代理逻辑+被代理对象的原处理逻辑
     */
    public static void test_dynamic_proxy_base() {

        Humen originalHuman = new HumanImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(originalHuman);
        Humen proxyInstance = (Humen) Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), new Class[]{Humen.class}, dynamicProxy);

        originalHuman.eat("originalHuman:eat");
        proxyInstance.eat("proxyInstance:eat");

    }

    /**
     * 1.测试：可以直接调用逻辑体，即代理逻辑；只是没有意义而已，这个过程由jkd的动态代理机制来完成就好了。
     * 2.注意：要想直
     */
    public static void test_dynamic_proxy_invoke_directly_proxy_logic() {

        Humen originalHuman = new HumanImpl();
        DynamicProxy dynamicProxy = new DynamicProxy(originalHuman);

        try {
            dynamicProxy.invoke(null, Humen.class.getMethod("eat", new Class[]{String.class}), new Object[]{"directly invoke."});
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }


}
