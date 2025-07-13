package com.mbarekDev.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

//import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    //private final Logger logger = Logger.getLogger(getClass().getName());
    private static final Logger logger = LoggerFactory.getLogger(DemoLoggingAspect.class);

    // Pointcut for all methods in the controller package
    @Pointcut("execution(* com.mbarekDev.springboot.thymeleafdemo.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.mbarekDev.springboot.thymeleafdemo.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.mbarekDev.springboot.thymeleafdemo.dao.*.*(..))")
    private void forDaoPackage() {
    }


    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        // display method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("---> in @Before : calling method: {}", theMethod);

        // desplay the arguments to the method
        Object[] args = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            logger.info("---> Argument: {}", tempArg);
        }
    }


    // Advice that runs before any method in the controller package
    @Before("forControllerPackage()")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("---> in @Before : calling method: {}", theMethod);

        Object[] args = joinPoint.getArgs();
        for (Object tempArg : args) {
            logger.info("---> Argument: {}", tempArg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // display method that returning the result
        String theMethod = theJoinPoint.getSignature().toShortString();
        logger.info("-----> in @AfterReturning : from method: {}", theMethod);

        // display data that has been returned
        logger.info("-----> result: {}", theResult);
    }
}
