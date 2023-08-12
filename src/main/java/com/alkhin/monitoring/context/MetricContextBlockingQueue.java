package com.alkhin.monitoring.context;

import com.alkhin.monitoring.dto.MetricDto;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class MetricContextBlockingQueue {
    private final BlockingQueue<MetricDto> context = new LinkedBlockingQueue<>();
    public void incrementSuccess(String metricName, long time) {
        putMetric(metricName, false, time);
    }
    public void incrementError(String metricName) {
        putMetric(metricName, true, 0);
    }
    public MetricDto getMetric() throws InterruptedException {
        return context.take();
    }
    private void putMetric(String name, boolean error, long time) {
        context.add(new MetricDto(name, true, error, time));
    }
}
