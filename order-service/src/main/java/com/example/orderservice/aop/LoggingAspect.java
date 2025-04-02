package com.example.orderservice.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Pointcut("execution(* com.example.orderservice.service..*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logServiceMethod() {
        System.out.println("Service method called at: " + System.currentTimeMillis());
    }
}