package es.vir2al.fwk.fwk.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnExpression("${aspect.enabled:true}")
public class LoggingAspect {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    /*
    @Before(value="execution(* es.vir2al.fwk.app..*(..))")
    public void beforeAdvice(JoinPoint joinPoint) {
        LOGGER.info("=========== BEFORE ADVICE ================");
        LOGGER.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName());
    }

    @After(value="execution(* es.vir2al.fwk.app..*(..))")
    public void afterAdvice(JoinPoint joinPoint) {
        LOGGER.info("=========== AFTER ADVICE ================");
        LOGGER.info(joinPoint.getSignature().getDeclaringTypeName()+" "+joinPoint.getSignature().getName());   
    }
    */

    @Around(value="execution(* es.vir2al.fwk.app..*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Enter: {}.{}() with argument[s] = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            Object result = joinPoint.proceed();
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Exit: {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), result);
            }
            return result;
        } catch (IllegalArgumentException e) {
            LOGGER.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }

    }

}
