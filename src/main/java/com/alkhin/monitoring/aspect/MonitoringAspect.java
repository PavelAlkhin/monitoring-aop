package com.alkhin.monitoring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import com.alkhin.monitoring.service.MonitoringService;

import java.lang.reflect.Method;

@Aspect
public class MonitoringAspect {
    private final MonitoringService monitoringService;
    public MonitoringAspect(MonitoringService monitoringService) {
        this.monitoringService = monitoringService;
    }

    @Around("@annotation(com.alkhin.monitoring.aspect.Monitoring)")
    public Object methodTimeLogger(ProceedingJoinPoint proceedingJoinPoint) {

        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        Method method = methodSignature.getMethod();
        Monitoring annotation = method.getAnnotation(Monitoring.class);
        String metricName = annotation.NAMESPACE();

        long startTime = System.currentTimeMillis();

        Object proceed = null;

        try {
            proceed = proceedingJoinPoint.proceed();
            long stopTime = System.currentTimeMillis();
            monitoringService.incrementSuccess(metricName, stopTime - startTime);

        } catch (Throwable e) {
            monitoringService.incrementError(metricName);
        }

        return proceed;
    }
}
