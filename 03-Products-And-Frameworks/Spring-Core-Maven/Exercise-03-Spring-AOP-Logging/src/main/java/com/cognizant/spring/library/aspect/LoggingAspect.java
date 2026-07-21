package com.cognizant.spring.library.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.cognizant.spring.library.service.BookService.*(..))")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startNanos = System.nanoTime();
        try {
            return joinPoint.proceed();
        } finally {
            long elapsedMs = (System.nanoTime() - startNanos) / 1_000_000L;
            System.out.println("[AOP] " + joinPoint.getSignature().toShortString()
                    + " completed in " + elapsedMs + " ms");
        }
    }
}
