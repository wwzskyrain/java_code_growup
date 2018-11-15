package erik.spring.deep.aop.pointcut;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import java.io.Serializable;

public class TruePointCut implements Pointcut,Serializable {

    @Override
    public ClassFilter getClassFilter() {
        return ClassFilter.TRUE;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return MethodMatcher.TRUE;
    }
}
