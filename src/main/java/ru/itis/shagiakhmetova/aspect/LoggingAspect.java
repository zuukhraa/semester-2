package ru.itis.shagiakhmetova.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

//        @Pointcut("execution(* ru.itis.shagiakhmetova.controller.WeatherController.getAppealsByUser())")
//    public void logRequestByUser() {
//    }

    @Pointcut("@annotation(Loggable)")
    public void logRequestByUser() {
    }

    @Around("logRequestByUser()")
    public Object logAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
    long startTime = System.currentTimeMillis();
    Object object = proceedingJoinPoint.proceed();
    LOGGER.info("User request start Time: " + startTime);
    return object;
    }
}
