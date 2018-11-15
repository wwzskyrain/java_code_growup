package jdk.util.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.InvocationHandler;

import java.lang.reflect.Method;

public class CGLibProxy4 {

    public static void main(String[] args) {

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);
        enhancer.setCallback(new InvocationHandler() {  //这个类几乎完全等同于java.lang.reflect.InvocationHandler
//  但是InvocationHandler是不可能调原方法的，因为没有target类实例啊。
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {



                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "hello cglib";
                } else {
                    throw new RuntimeException("Do not know what to do");
                }
            }
        });

        SampleClass proxy = (SampleClass) enhancer.create();
        proxy.test(null);
        proxy.toString();
    }

}
