package com.rest.JobApp.AOP;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class PerformanceAspect {
    private static final Logger logger= LoggerFactory.getLogger(PerformanceAspect.class);

   @Around("execution(* com.rest.JobApp.service.JobService.getJob(..))")
    public Object getResponseTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
              Object object=proceedingJoinPoint.proceed();
        long endTime=System.currentTimeMillis();
       logger.info(" time taken to respond a single post for the {}() method is  {} milli seconds", proceedingJoinPoint.getSignature().getName(), endTime - startTime);
        return object;
    }
}
