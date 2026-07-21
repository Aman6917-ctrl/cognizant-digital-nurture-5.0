package com.cognizant.spring.library.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Pointcut("execution(* com.cognizant.spring.library.service.BookService.*(..))")
    public void bookServiceMethods() {
    }

    @Before("bookServiceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("[AOP @Before] Entering " + joinPoint.getSignature().toShortString());
    }

    @AfterReturning(pointcut = "bookServiceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("[AOP @AfterReturning] " + joinPoint.getSignature().getName()
                + " returned: " + result);
    }

    @AfterThrowing(pointcut = "bookServiceMethods()", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        System.out.println("[AOP @AfterThrowing] " + joinPoint.getSignature().getName()
                + " threw " + error.getClass().getSimpleName() + ": " + error.getMessage());
    }
}
