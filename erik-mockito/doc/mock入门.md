# 1.基本使用原理
1.  在写单元测试的时候，我们往往只关注，也只需要关注某一个功能点
是否符合我们预期，即在指定输入下得到我们预期的输出；而该功能点往往
是有上下文的，但是为了减少上下文的依赖，我们就希望能够指定上下文；
为此，当然我们可以手动编码，但是有时构造一个参数是很浪费的，因为
在当前测试中只会用到该参数的一个方法，所以这时候可以用mock来指定：
当X输入时，参数将是Y输出。
2.  简单而言，mock的主要工作就是"定义具体输入下的输出"。然而，
当具体到java的编码中，这里面就有很多细节要做了。


# 2.笔记
1.  默认情况下，当调用一个mock对象的非stubbed方法时，将返回"方法返回值
类型的默认赋值"，比如，引用类型则返回null，基本类型返回0、false等。
2.  stub是可以被重写覆盖的，但是"重写"stub是怀代码的味道
3.  被stub的方法无论调用多少次都返回"预订"的返回值，除非定义了`consecutive call`。
4.  在junit5中使用@Mock注解来注解成员变量，需引入依赖`org.mockito:mockito-junit-jupiter:2.23.4`,
并且要在测试类上引用`@ExtendWith(MockitoExtension.class)`
5.  注意，consecutive不会overriding stub
6.  when(mock.something()).then(answer);这个形式可以实现"主次回调"，
但是官方并不推荐使用，因为形式`when(mock.someMethod()).thenReturn()`，`when(mock.someMethod()).thenThrow()`已经够用了；
7.  
  
 

