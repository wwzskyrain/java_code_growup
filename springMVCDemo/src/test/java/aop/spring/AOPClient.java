package aop.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring/local/appcontext-spring-aop.xml")
public class AOPClient {

    @Autowired
    // 必须指定使用代理对象名称, 否则不予代理
    @Qualifier("serviceProxy")
    private OrderService service;

    @Test
    public void client() {

        service.save();
        service.delete(88);

    }

}
