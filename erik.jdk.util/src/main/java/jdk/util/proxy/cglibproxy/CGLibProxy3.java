package jdk.util.proxy.cglibproxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.FixedValue;

public class CGLibProxy3 {

    public static void main(String[] args) {    //测试FixedValue这个Callback。

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(SampleClass.class);

        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "Hello cglib";
            }
        });
        SampleClass proxy = (SampleClass) enhancer.create();
        System.out.println("proxy.test:" + proxy.test(null)); //拦截test，输出Hello cglib
        System.out.println("proxy.toString:" + proxy.toString());
        System.out.println("proxy.getClass" + proxy.getClass());
        System.out.println("proxy.hashCode" + proxy.hashCode());

    }

}
