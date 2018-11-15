package beanpostprocessor;

import org.springframework.beans.factory.InitializingBean;

public class Chinese
        implements Person, InitializingBean {
    private Axe axe;
    private String name;

    public Chinese() {
        System.out.println("Chinese() 构造函数 ");
    }

    public void setAxe(Axe axe) {
        System.out.println("Spring执行setAxe()方法注入依赖关系... with Axe:" + axe);
        this.axe = axe;
    }

    public void setName(String name) {
        System.out.println("Spring执行setName()方法注入依赖关系... with name:" + name);
        this.name = name;
    }

    public void useAxe() {
        System.out.println(name + axe.chop());
    }

    // 下面是两个生命周期方法
    public void init() {
        System.out.println("正在执行初始化方法 init...");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println("正在执行初始化方法 afterPropertiesSet...");
    }
}