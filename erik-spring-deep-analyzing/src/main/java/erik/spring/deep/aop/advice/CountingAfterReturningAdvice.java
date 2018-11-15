package erik.spring.deep.aop.advice;

import erik.spring.deep.aop.util.MethodCounter;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class CountingAfterReturningAdvice extends MethodCounter implements AfterReturningAdvice {

    @Override
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        count(method);
    }
}

