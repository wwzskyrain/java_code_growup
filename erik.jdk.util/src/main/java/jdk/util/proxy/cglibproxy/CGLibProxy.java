package jdk.util.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibProxy implements MethodInterceptor {


    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before ...");

        System.out.println("method:"+method.toGenericString());

        System.out.println("methodProxy:"+methodProxy.toString());

//        methodProxy.invoke(o,objects);  //调用循环了，自己调用自己，会java.lang.StackOverflowError异常。

//        method.invoke(o,objects);   //InvocationTargetException     java.lang.StackOverflowError

        Object result = methodProxy.invokeSuper(o, objects);

        //  相当于Object result = methodProxy.invoke(new HumanImpl(), objects);

        //  由于CGLib只是对HumenImple类型做代理，并不是对HumenImple的某个实例（对象）做代理，所以invokeSuper也相当于：
        //    Object result = method.invoke(new HumanImpl(), objects);

        System.out.println("afterreturning ...");

        return result;

    }

    public static void main(String[] args) {

        CGLibProxy cgLibProxy = new CGLibProxy();

        HumenImpl humenImplProxy = cgLibProxy.getProxy(HumenImpl.class);
        //这只是对HumenImple类型做代理，并不是对HumenImple的某个实例（对象）做代理。

        humenImplProxy.eat("rice");

    }
}
