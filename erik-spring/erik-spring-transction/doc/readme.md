# 1.大纲
1.  无transaction管理时，在多个DB操作之间如果报错的会啊，报错之后的代码就不会执行了；
    而错误的是，之前的db操作也不会回滚。
2.  所以需要transaction管理。
3.  Spring提供了transaction的api，这些api都有各种实现，这一点单单从使用的角度来说
    不用关心；我们关心的是结合api的使用模型，spring为此提供了三种模型吧，分别是
    1.  编程式，熟悉底层使用方式及数据库事务的原理
    2.  *声明式之aop配置形式，学习使用形式
    3.  *声明式之注解形式，学习使用形式
# 2.几点笔记
1.  声明式事务和编程式事务的区别
    ```
    编程式事务处理：所谓编程式事务指的是通过编码方式实现事务，允许用户在代码中精确定义事务的边界。
    即类似于JDBC编程实现事务管理。
    管理使用TransactionTemplate或者直接使用底层的PlatformTransactionManager。
    对于编程式事务管理，spring推荐使用TransactionTemplate。

    声明式事务处理：管理建立在AOP之上的。其本质是对方法前后进行拦截，
    然后在目标方法开始之前创建或者加入一个事务，在执行完目标方法之后根据执行情况提交或者回滚事务。
    声明式事务最大的优点就是不需要通过编程的方式管理事务，
    这样就不需要在业务逻辑代码中掺杂事务管理的代码，只需在配置文件中做相关的事务规则声明
    (或通过基于@Transactional注解的方式)，便可以将事务规则应用到业务逻辑中。

    简单地说，编程式事务侵入到了业务代码里面，但是提供了更加详细的事务管理-事务控制粒度灵活；
    而声明式事务由于基于AOP，所以既能起到事务管理的作用，又可以不影响业务代码的具体实现，
    但是事务控制粒度比较粗。
    ```

参考资料
1.  [Spring详解（八）------事务管理](https://www.cnblogs.com/ysocean/p/7617620.html)
2.  [Data Access- Transaction Management](https://docs.spring.io/spring/docs/5.1.4.RELEASE/spring-framework-reference/data-access.html#transaction)

