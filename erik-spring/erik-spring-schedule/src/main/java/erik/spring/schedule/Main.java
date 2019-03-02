package erik.spring.schedule;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * @author erik.wang
 * @date 2019/03/02
 **/
public class Main {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");
        TaskSchedulerExample taskSchedulerExample = applicationContext.getBean(TaskSchedulerExample.class);

        taskSchedulerExample.execute();



    }

}
