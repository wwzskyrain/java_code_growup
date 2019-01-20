package erik.spring.transaction.bootstrap;

import erik.spring.transaction.bootstrap.config.DaoConfig;
import erik.spring.transaction.bootstrap.config.DataSourceConfig;
import erik.spring.transaction.bootstrap.config.ServiceConfig;
import erik.spring.transaction.bootstrap.config.DataSourceTransactionManagerConfig;
import erik.spring.transaction.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

public class MainProgrammingTransaction {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(
                DaoConfig.class,
                DataSourceConfig.class,
                ServiceConfig.class,
                DataSourceTransactionManagerConfig.class
        );


        AccountService accountService = context.getBean(AccountService.class);

        String outer = "tom";
        String inner = "merry";
        accountService.transfer(outer,inner,new BigDecimal("1000"));


    }

}
