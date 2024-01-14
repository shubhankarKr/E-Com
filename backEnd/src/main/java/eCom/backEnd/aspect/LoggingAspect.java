package eCom.backEnd.aspect;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

	private static final Logger logger = LogManager.getLogger(LoggingAspect.class);

	@Around(value = "execution(* eCom.backEnd.controller.*.*(..))")
	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
		Instant start = Instant.now();
		Object cust = joinPoint.proceed();
		Long taken = start.until(Instant.now(), ChronoUnit.MILLIS);
		logger.info(" Time taken is : " + taken + " ms");

		return cust;
	}

}
