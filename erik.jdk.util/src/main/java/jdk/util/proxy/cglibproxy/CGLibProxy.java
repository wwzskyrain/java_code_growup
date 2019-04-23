package jdk.util.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class CGLibProxy implements MethodInterceptor {


    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }

    @Override
    /**
     * 1.proxy是代理对象（请先明确代理"代理对象"，"原对象"，代理逻辑 三个名词）
     * 2.method是"原对象类型的method
     * 3.objects是调用参数
     * 4.methodProxy是代理对象类型的method
     * 5.如果用method去作用到一个不是它所属类型的实例对象上，会报运行时异常"IllegalAccessException"
     * 6.++ proxy是"原类型"的子类型的实例，这就是他的代理原理。所以，不能对一个final类型做cglib代理
     * 7.有必要注意下，正常的intercept实现是：调用前后编写业务增强逻辑，中间调用原方法，调用原方法的规范方法：
     * 8.调用原方法的规范方法：methodProxy(proxy, objects).
     */
    public Object intercept(Object proxy, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {

        System.out.println("before ...");

        System.out.println("o.getClass() " + proxy.getClass());
        System.out.println("o.getClass().getSuperclass() " + proxy.getClass().getSuperclass());
        System.out.println("o.getClass().getInterfaces() " + Arrays.toString(proxy.getClass().getInterfaces()));

        System.out.println("method:" + method.toGenericString());

        System.out.println("methodProxy:" + methodProxy.toString());

//        methodProxy.invoke(proxy,objects);
// 调用循环了，proxy自己调用自己，会java.lang.StackOverflowError异常。

//        method.invoke(proxy,objects);
// 这里也调用循环了。因为这里也是proxy调用它自己的方法。

        Object result = methodProxy.invokeSuper(proxy, objects);
//      Object result = methodProxy.invokeSuper(new HumanImpl(), objects);
//      methodProxy所属类型是被代理类型的子类型，而不是代理类型，所以当作用到代理原类型示例就会报错：IllegalArgumentException

        System.out.println("afterreturning ...");

        return result;

    }

    public static void main(String[] args) {

        test_cglib_base();
        test_method_invoke_other_type_object();
    }

    /**
     * 1.这只是对HumenImple类型做代理，并不是对HumenImple的某个实例（对象）做代理。
     * 2.
     */
    public static void test_cglib_base() {

        CGLibProxy cgLibProxy = new CGLibProxy();
        HumanImpl humanImplProxy = cgLibProxy.getProxy(HumanImpl.class);
        humanImplProxy.eat("rice");
    }

    /**
     * method作用到不是它所属类型的对象上时，报错：IllegalArgumentException: object is not an instance of declaring class
     * 这一点很容易理解。
     */
    public static void test_method_invoke_other_type_object(){

        try {
            Method humanImplMethod = HumanImpl.class.getMethod("eat", new Class[]{String.class});
            try {
                humanImplMethod.invoke(new Object(),"invoke");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
