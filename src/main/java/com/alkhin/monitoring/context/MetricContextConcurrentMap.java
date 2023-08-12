package com.alkhin.monitoring.context;

import com.alkhin.monitoring.metric.Metric;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MetricContextConcurrentMap {
    private final Map<String, Metric> context = new ConcurrentHashMap<>();
    private final boolean register;

    public MetricContextConcurrentMap(boolean register) {
        this.register = register;
    }

    public void incrementSuccess(String metricName, long timer) {
        Metric metric = getMetric(metricName);
        metric.incrementCounter();
        metric.addTime(timer);
    }

    public void incrementError(String metricName) {
        Metric metric = getMetric(metricName);
        metric.incrementCounterErrors();
        metric.incrementCounter();
    }

    public Metric getMetric(String metricName) {
        return context.computeIfAbsent(metricName, k -> new Metric(metricName, register));
    }

    public Map<String, Metric> getContext() {
        return context;
    }
}
