package ru.bell.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

@Slf4j
@Aspect
@Service
public class AspectDAO {

    @Around("execution(* ru.bell.dao.IncomeInvoiceDAO.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("INVOCATION METHOD: " + joinPoint.getSignature().toShortString());

        Object returnValue = joinPoint.proceed();

        log.info("METHOD: " + joinPoint.getSignature().toShortString() + " RETURNS: " + returnValue);
        return returnValue;
    }
}