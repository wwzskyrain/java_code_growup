package aop.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ConcreteInterceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("前置通知 -> ");

        Object result = invocation.proceed();

        System.out.println("<- 后置通知");

        return result;
    }

}
