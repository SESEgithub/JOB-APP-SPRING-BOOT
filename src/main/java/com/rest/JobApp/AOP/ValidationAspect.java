package com.rest.JobApp.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
    private static final Logger logger= LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* com.rest.JobApp.service.JobService.getJob(..)) && args(postId) ")
    public Object validate(ProceedingJoinPoint proceedingJoinPoint, int postId) throws Throwable {

       if (postId<0){
           postId=-postId;
       }
      Object object= proceedingJoinPoint.proceed(new Object[]{postId});
       return object;
    }
}
