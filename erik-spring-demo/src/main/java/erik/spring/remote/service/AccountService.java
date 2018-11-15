package erik.spring.remote.service;

import erik.spring.remote.entity.Account;

public interface AccountService {


    void insert(Account account);

    Account getAccount();

}
