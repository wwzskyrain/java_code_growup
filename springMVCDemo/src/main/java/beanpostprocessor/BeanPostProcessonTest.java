package beanpostprocessor;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class BeanPostProcessonTest {
    public static void main(String[] args) throws Exception {

        load_with_application_context();
//        load_with_bean_factory();

    }

    public static void load_with_application_context() {

        // 以类加载路径下的beans.xml文件来创建Spring容器

        ApplicationContext ctx = new
                ClassPathXmlApplicationContext("beans.xml");
//        Person p = (Person) ctx.getBean("chinese");   //bean非lazy的初始化的
    }

    public static void load_with_bean_factory(){
        // 搜索类加载路径下的beans.xml文件创建Resource对象
        Resource isr = new ClassPathResource("beans.xml");
        // 创建默认的BeanFactory容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 让默认的BeanFactory容器加载isr对应的XML配置文件
        new XmlBeanDefinitionReader(beanFactory).loadBeanDefinitions(isr);
        // 获取容器中的Bean后处理器
        BeanPostProcessor bp = (BeanPostProcessor) beanFactory.getBean("bp");
        // 注册Bean后处理器
        beanFactory.addBeanPostProcessor(bp);
        Person p = (Person) beanFactory.getBean("chinese");

        p.useAxe();
    }

}
