package com.alkhin.monitoring.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import com.alkhin.monitoring.service.MapMonitoringService;

public class MonitoringAspectTest {

    @Mock
    private ProceedingJoinPoint proceedingJoinPoint;

    private final MonitoringAspect aspect = new MonitoringAspect(new MapMonitoringService(false));

    @Test
    public void testMethodTimeLogger() throws Throwable {

        Assertions.assertNotNull(aspect);

//        aspect.methodTimeLogger(proceedingJoinPoint);

//        CustomerAdapterReactiveKafkaServerMessageListener.class;

//        when(proceedingJoinPoint.getSignature()).thenReturn();

//        verify(proceedingJoinPoint, times(1)).proceed();




    }
}