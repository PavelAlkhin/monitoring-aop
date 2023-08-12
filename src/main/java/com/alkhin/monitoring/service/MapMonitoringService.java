package com.alkhin.monitoring.service;

import com.alkhin.monitoring.context.MetricContextConcurrentMap;

public class MapMonitoringService implements MonitoringService {
    private final MetricContextConcurrentMap metricContextConcurrentMap;

    public MapMonitoringService(boolean register) {
        this.metricContextConcurrentMap = new MetricContextConcurrentMap(register);
    }

    public void incrementSuccess(String metricName, long time) {
        metricContextConcurrentMap.incrementSuccess(metricName, time);
    }

    public void incrementError(String metricName) {
        metricContextConcurrentMap.incrementError(metricName);
    }

    @Override
    public void startConsumer() {

    }
}
