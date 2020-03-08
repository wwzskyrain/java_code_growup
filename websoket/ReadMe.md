# 1.配置启动及注意事项
1.  写一个用`@ServerEndpoint(value = "/websocket/chat")`所注解的类ChatEndPoint
2.  将该类编译后的class放到`webapp/WEB-INF/classes`下；这一步直接用`mvn install`打包就好了.
3.  写一个html文件，里面用javascript来完成websocket-client的编写，放在`webapp`下，以便以静态方式的形式直接访问，比如`http://localhost:8029/chatClient.html`
4.  先启动服务端，注意用idea的调试功能就可以；再在浏览器中访问`http://localhost:8029/chatClient.html`；

注意：
    
1.注解@ServerEndpoint 等注解 需要引入依赖
   
```xml
     <dependency>
        <groupId>javax.websocket</groupId>
        <artifactId>javax.websocket-api</artifactId>
        <version>1.1</version>
        <scope>provided</scope>
    </dependency>
```
    

# 2.项目结构
1.  很明显也是Client/Server结构。websocket-client-api和websocket-server-api分别有javascript何javax来提供了
2.  这个项目的具体业务是：
    1.  每一个通过浏览器访问`http://localhost:8029/chatClient.html`的都是一个client。
    2.  多个client共同组成一个聊天室，一个client发消息，服务端就将消息转发给所有client。
    3.  服务端还会广播"新client加入聊天室"和"某个client退出聊天室"的消息。
