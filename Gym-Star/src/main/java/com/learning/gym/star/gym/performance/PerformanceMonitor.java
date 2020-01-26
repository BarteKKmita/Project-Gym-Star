package com.learning.gym.star.gym.performance;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class PerformanceMonitor {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Around("execution(public * com.learning.gym.star.gym.controller.jpa.GymControllerJpa.*(..))")
    public Object logControllerJpaPerformance(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        final StopWatch stopWatch = new StopWatch();
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        stopWatch.start();
        Object objectProceeded = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("Execution time of " + className + "." + methodName + " takes :: " + stopWatch.getTotalTimeMillis() + " ms");
        return objectProceeded;
    }
}

