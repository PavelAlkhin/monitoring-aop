package com.alkhin.monitoring.service;

import com.alkhin.monitoring.consumer.QueueMetricsConsumer;
import com.alkhin.monitoring.context.MetricContextBlockingQueue;

public final class QueueMonitoringProducerService implements MonitoringService {
    private final MetricContextBlockingQueue metricContextBlockingQueue;
    private final QueueMetricsConsumer queueMetricsConsumer;

    public QueueMonitoringProducerService(MetricContextBlockingQueue metricContextBlockingQueue, QueueMetricsConsumer queueMetricsConsumer) {
        this.metricContextBlockingQueue = metricContextBlockingQueue;
        this.queueMetricsConsumer = queueMetricsConsumer;
    }

    public void incrementSuccess(String metricName, long time) {
        metricContextBlockingQueue.incrementSuccess(metricName, time);
    }

    public void incrementError(String metricName) {
        metricContextBlockingQueue.incrementError(metricName);
    }

    public void startConsumer(){
        queueMetricsConsumer.run();
    }
}
