package erik.spring.remote.entity;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 6211675436459336720L;
    private String name;

    private Integer account;

    public Account() {
    }

    public Account(String name, Integer account) {
        this.name = name;
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAccount() {
        return account;
    }

    public void setAccount(Integer account) {
        this.account = account;
    }
}
