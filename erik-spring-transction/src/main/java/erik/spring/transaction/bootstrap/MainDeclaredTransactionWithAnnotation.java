package erik.spring.transaction.bootstrap;

import erik.spring.transaction.bootstrap.config.*;
import erik.spring.transaction.service.AccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;

/**
 * 声明式事务之"注解"形式
 */
public class MainDeclaredTransactionWithAnnotation {

    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(
                DaoConfig.class,
                DataSourceConfig.class,
                ServiceConfig.class,
                DataSourceTransactionManagerConfig.class,
                TransactionAnnotationConfig.class
        );


        AccountService accountService = context.getBean(AccountService.class);

        String outer = "tom";
        String inner = "merry";
        accountService.transfer(outer, inner, new BigDecimal("1000"));

    }

}
