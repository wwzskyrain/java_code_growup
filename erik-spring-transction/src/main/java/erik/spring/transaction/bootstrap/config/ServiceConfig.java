package erik.spring.transaction.bootstrap.config;

import erik.spring.transaction.service.AccountService;
import erik.spring.transaction.service.impl.AccountServiceImplWithTransactionTemplate;
import erik.spring.transaction.service.impl.AccountServiceWithTransactional;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public AccountService accountService(){

        AccountService accountService = new AccountServiceWithTransactional();
        return accountService;
    }
}
