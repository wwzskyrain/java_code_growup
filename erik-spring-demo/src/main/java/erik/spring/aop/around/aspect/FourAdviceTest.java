package erik.spring.aop.around.aspect;

import org.aspectj.lang.annotation.*;
import org.aspectj.lang.*;

import java.util.Arrays;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 *
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
// 定义一个切面
@Aspect
public class FourAdviceTest {
    // 定义Around增强处理
    @Around("execution(* erik.spring.aop.impl.*.*(..))")
    public Object processTx(ProceedingJoinPoint jp)
            throws java.lang.Throwable {
        System.out.println("Around增强：执行目标方法之前，模拟开始事务...");
        // 访问执行目标方法的参数
        Object[] args = jp.getArgs();
        // 当执行目标方法的参数存在，
        // 且第一个参数是字符串参数
        if (args != null && args.length > 0
                && args[0].getClass() == String.class) {
            // 修改目标方法调用参数的第一个参数
            args[0] = "【增加的前缀】" + args[0];
        }
        //执行目标方法，并保存目标方法执行后的返回值
        Object rvt = jp.proceed(args);
        System.out.println("Around增强：执行目标方法之后，模拟结束事务...");
        // 如果rvt的类型是Integer，将rvt改为它的平方
        if (rvt != null && rvt instanceof Integer)
            rvt = (Integer) rvt * (Integer) rvt;
        return rvt;
    }

    // 定义Before增强处理
    @Before("execution(* erik.spring.aop.impl.*.*(..))")
    public void authority(JoinPoint jp) {
        System.out.println("Before增强：模拟执行权限检查");
        // 返回被织入增强处理的目标方法
        System.out.println("Before增强：被织入增强处理的目标方法为："
                + jp.getSignature().getName());
        // 访问执行目标方法的参数
        System.out.println("Before增强：目标方法的参数为："
                + Arrays.toString(jp.getArgs()));
        // 访问被增强处理的目标对象
        System.out.println("Before增强：被织入增强处理的目标对象为："
                + jp.getTarget());
    }

    //定义AfterReturning增强处理
    @AfterReturning(pointcut = "execution(* erik.spring.aop.impl.*.*(..))"
            , returning = "rvt")
    public void log(JoinPoint jp, Object rvt) {
        System.out.println("AfterReturning增强：获取目标方法返回值:"
                + rvt);
        System.out.println("AfterReturning增强：模拟记录日志功能...");
        // 返回被织入增强处理的目标方法
        System.out.println("AfterReturning增强：被织入增强处理的目标方法为："
                + jp.getSignature().getName());
        // 访问执行目标方法的参数
        System.out.println("AfterReturning增强：目标方法的参数为："
                + Arrays.toString(jp.getArgs()));
        // 访问被增强处理的目标对象
        System.out.println("AfterReturning增强：被织入增强处理的目标对象为："
                + jp.getTarget());
    }

    // 定义After增强处理
    @After("execution(* erik.spring.aop.impl.*.*(..))")
    public void release(JoinPoint jp) {
        System.out.println("After增强：模拟方法结束后的释放资源...");
        // 返回被织入增强处理的目标方法
        System.out.println("After增强：被织入增强处理的目标方法为："
                + jp.getSignature().getName());
        // 访问执行目标方法的参数
        System.out.println("After增强：目标方法的参数为："
                + Arrays.toString(jp.getArgs()));
        // 访问被增强处理的目标对象
        System.out.println("After增强：被织入增强处理的目标对象为："
                + jp.getTarget());
    }
}
