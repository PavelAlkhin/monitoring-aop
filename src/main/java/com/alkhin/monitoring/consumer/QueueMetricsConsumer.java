package com.alkhin.monitoring.consumer;

import com.alkhin.monitoring.dto.MetricDto;
import com.alkhin.monitoring.metric.Metric;
import com.alkhin.monitoring.context.MetricContextBlockingQueue;

import java.util.HashMap;
import java.util.Map;

public class QueueMetricsConsumer {
    private final MetricContextBlockingQueue metricContextBlockingQueue;
    private final Map<String, Metric> context = new HashMap<>();
    private final boolean register;
    public QueueMetricsConsumer(MetricContextBlockingQueue metricContextBlockingQueue, boolean register) {
        this.metricContextBlockingQueue = metricContextBlockingQueue;
        this.register = register;
    }
    public void run() {
        new Thread(() -> {
            try {
                while (true) {
                    MetricDto metricDto = metricContextBlockingQueue.getMetric();
                    Metric metric = getMetric(metricDto.name());
                    if (metricDto.error()) {
                        metric.incrementCounterErrors();
                    }
                    if (metricDto.invoke()) {
                        metric.incrementCounter();
                    }
                    metric.addTime(metricDto.time());
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
    public Map<String, Metric> getContext(){
        return context;
    }
    private Metric getMetric(String metricName) {
        Metric metric = context.get(metricName);
        if (metric == null) {
            metric = new Metric(metricName, register);
            context.put(metricName, metric);
        }
        return metric;
    }
}