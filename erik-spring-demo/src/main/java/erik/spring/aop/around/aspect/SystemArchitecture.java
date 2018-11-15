package erik.spring.aop.around.aspect;

import org.aspectj.lang.annotation.*;
/**
 * Description:
 * <br/>ÍøÕ¾: <a href="http://www.crazyit.org">·è¿ñJavaÁªÃË</a>
 * <br/>Copyright (C), 2001-2016, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author Yeeku.H.Lee kongyeeku@163.com
 * @version 1.0
 */
@Aspect
public class SystemArchitecture
{
	@Pointcut("execution(* erik.spring.aop.impl.*.*(..))")
	public void myPointcut(){}
}

