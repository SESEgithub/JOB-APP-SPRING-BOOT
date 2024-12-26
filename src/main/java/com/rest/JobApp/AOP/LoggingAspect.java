package com.rest.JobApp.AOP;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger logger=  LoggerFactory.getLogger(LoggingAspect.class);

   @Before("execution(* com.rest.JobApp.service.JobService.getAllJobs(..))")
    public void logMethodCall(JoinPoint join){
        logger.info("the "+ join.getSignature().getName()+" method is called");
    }

    @After("execution(* com.rest.JobApp.service.JobService.getAllJobs(..))")
    public void logMethodEx(JoinPoint join){
        logger.info("the "+ join.getSignature().getName()+" method is executed");
    }

    @AfterThrowing("execution(* com.rest.JobApp.service.JobService.getAllJobs(..))")
    public void logMethodThrow(JoinPoint join){
        logger.info("the "+ join.getSignature().getName()+" method throws an exception ");
    }

    @AfterReturning("execution(* com.rest.JobApp.service.JobService.getAllJobs(..))")
    public void logMethodReturn(JoinPoint join){
        logger.info("the "+ join.getSignature().getName()+" method returns successfully ");
    }

}
