package com.alkhin.monitoring.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MapMonitoringServiceTest {
    MapMonitoringService mapMonitoringService = new MapMonitoringService(false);

    @Test
    public void testIncrementSuccess() {
        mapMonitoringService.incrementSuccess("test", 1);
        mapMonitoringService.incrementError("test");
        mapMonitoringService.startConsumer();
        Assertions.assertNotNull(mapMonitoringService);
    }
}