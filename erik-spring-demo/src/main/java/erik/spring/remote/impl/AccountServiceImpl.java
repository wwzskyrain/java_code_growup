package erik.spring.remote.impl;

import erik.spring.remote.entity.Account;
import erik.spring.remote.service.AccountService;

public class AccountServiceImpl implements AccountService {

    @Override
    public void insert(Account account) {
        System.out.println("AccountServiceImpl.insert()");
    }

    @Override
    public Account getAccount() {
        return new Account("erik.wang", 28);
    }
}
