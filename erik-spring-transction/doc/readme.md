1.  大纲
    1.  无transaction管理时，在多个DB操作之间如果报错的会啊，报错之后的代码就不会执行了；
        而错误的是，之前的db操作也不会回滚。
    2.  所以需要transaction管理。
    3.  Spring提供了transaction的api，这些api都有各种实现，这一点单单从使用的角度来说
        不用关系；我们关心的是结合api的使用模型，spring为此提供了三种模型吧，分别是
        1.  编程式，熟悉底层使用方式及数据库事务的原理
        2.  *声明式之aop配置形式，学习使用形式
        3.  *声明式之注解形式，学习使用形式


参考资料
1.  [Spring详解（八）------事务管理](https://www.cnblogs.com/ysocean/p/7617620.html)
2.  [Data Access- Transaction Management](https://docs.spring.io/spring/docs/5.1.4.RELEASE/spring-framework-reference/data-access.html#transaction)

