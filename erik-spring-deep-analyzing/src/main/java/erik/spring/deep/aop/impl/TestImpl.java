package erik.spring.deep.aop.impl;

import erik.spring.deep.aop.inter.TestInterface;

public class TestImpl implements TestInterface {

    @Override
    public void test() {
        StackTraceElement currentStack = Thread.currentThread().getStackTrace()[1];
        System.out.printf("className:%s,methodName:%s\n",currentStack.getClassName(),currentStack.getMethodName());
    }
}
