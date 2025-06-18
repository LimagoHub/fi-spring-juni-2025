package de.fi.webapp.aspects;

import de.fi.webapp.services.PersoneServiceException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggerAspect {



    @Before(value="PointCuts.dozentMethods()")
    public void logAdvice(final JoinPoint joinPoint) throws PersoneServiceException {
        log.warn(String.format(
                "##################### Methode  %s wurde aufgerufen ########################"
                , joinPoint.getSignature().getName()));
    }

    @AfterReturning(value = "PointCuts.personenQueryControllerMethods()", returning = "result")
    public void logAdviceAfterReturning(final JoinPoint joinPoint, Object result) throws PersoneServiceException {

        log.warn(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        log.warn(String.format("############################# Result: %s ######################", result.toString()));
    }

    @After(value="execution(public * de.fi.webapp.presentation.v1.PersonenQueryController.*(..))")
    public void logAdviceAfter(final JoinPoint joinPoint) throws PersoneServiceException {

        log.warn(String.format("############################# After %s ######################", joinPoint.getSignature().getName()));

    }

    @AfterThrowing(value="execution(public * de.fi.webapp.presentation.v1.PersonenQueryController.*(..))", throwing = "ex")
    public void afterThrowing(final JoinPoint joinPoint, Throwable ex) {

        log.warn(String.format("############################# Afterreturning: %s ######################", joinPoint.getSignature().getName()));
        log.warn(String.format("############################# Exception: %s ######################", ex.toString()));
    }

    @Around(value="execution(public * de.fi.webapp.presentation.v1.PersonenQueryController.*(..))")
    public Object around(final ProceedingJoinPoint joinPoint) throws Throwable {
        return joinPoint.proceed();
    }
}
