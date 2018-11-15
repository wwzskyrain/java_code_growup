package erik.spring.deep.aop.advisor;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractGenericPointcutAdvisor;

import java.io.Serializable;

public class CountingMethodAdvisor extends AbstractGenericPointcutAdvisor implements Serializable {

    private Pointcut pointcut = Pointcut.TRUE;

    @Override
    public Pointcut getPointcut() {
        return null;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut == null ? Pointcut.TRUE : pointcut;

    }


}
