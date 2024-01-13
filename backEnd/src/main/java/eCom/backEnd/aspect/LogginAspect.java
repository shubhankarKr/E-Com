package eCom.backEnd.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogginAspect {

	@Around("execution(* com.backEnd.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("Before proceeding part of the Around advice." + joinPoint.getSignature());
		Object cust = joinPoint.proceed();
		System.out.println("After proceeding part of the Around advice.");
		return cust;
	}

}
