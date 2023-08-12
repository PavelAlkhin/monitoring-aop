package com.alkhin.monitoring.service;

public interface MonitoringService {
    void incrementSuccess(String metricName, long time);
    void incrementError(String metricName);
    void startConsumer();
}