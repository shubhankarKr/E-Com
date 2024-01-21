package eCom.backEnd.aspect;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;

public class LoggingAspect {

	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);
	

	@Around(value = "execution(* eCom.backEnd.controller.*.*(..))")
	public Object executionTIme(ProceedingJoinPoint joinPoint) throws Throwable {
		Long startTimeInMillis = System.currentTimeMillis();
		Object cust = joinPoint.proceed();
		Long endTimeInMillis = System.currentTimeMillis();
		logger.info(" executed Method {} in {} ms", joinPoint.getSignature().getName(),
				(endTimeInMillis - startTimeInMillis));
		return cust;
	}

	@AfterReturning(value = "execution(* eCom.backEnd.controller.*.*(..))")
	public void AfterCompleMethod(JoinPoint joinPoint) {
		logger.info(" completed method {} ", joinPoint.getSignature().getName());
	}

}
