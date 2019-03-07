# 1.概括
1.  日志使用抽象
    1.  在程序代码的关键处输出信息——打印日志，这是我们的最基本诉求，于是有了Logger这个接口。
    Logger提供了各种打印日志的方法，供程序员在编码中随手使用。
    2.  日志最终在打印在哪里？这是我们关心的第二个问题。于是有了Appender。在Appender中，
    又提出了要对日志进行被格式化输出，于是有了Layout；
    3.  我们想给日志本身分级别，以便于控制他们：要不要他们真的被输出，要他们各自输出到哪里。
    4.  。。。这些基本够用了，我们毕竟只是想打印日志；
# 2.log4f-2的实现
1.  对不起，详细细节并不太懂，不过可以进行一下知识梳理
    1.  日志配置文件在哪里？
    2.  Logger Hierarchy
    3.  Logger的Level
    4.  Logger的整体配置框架

# 2.1 Logger的整体配置框架及Best Practice


# 3.附录
## A.常用console日志配置
```
#日志级别,输出目的地
log4j.rootLogger=debug,stdout

log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target=System.out
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%c %d{ABSOLUTE} %5p %c{1}:%L - %m%n
```
