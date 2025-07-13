package com.mbarekDev.springboot.thymeleafdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    private final Logger logger = Logger.getLogger(getClass().getName());

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
        logger.info("---> in @Befor : calling method : " + theMethod);

        // desplay the arguments to the method


    }


    // Advice that runs before any method in the controller package
    /*@Before("forControllerPackage()")
    public void beforeControllerMethod(JoinPoint joinPoint) {
        String method = joinPoint.getSignature().toShortString();
        logger.info("=====>> Executing @Before advice on method: " + method);

        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("=====>> Argument: " + arg);
        }
    }*/
}
