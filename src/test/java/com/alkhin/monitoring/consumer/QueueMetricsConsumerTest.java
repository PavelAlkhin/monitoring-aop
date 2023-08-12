package com.alkhin.monitoring.consumer;

import com.alkhin.monitoring.metric.Metric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.alkhin.monitoring.context.MetricContextBlockingQueue;
import com.alkhin.monitoring.service.MonitoringService;
import com.alkhin.monitoring.service.QueueMonitoringProducerService;

import java.util.HashMap;
import java.util.Map;

import static java.lang.Thread.sleep;

public class QueueMetricsConsumerTest {
    private Map<String, Metric> context = new HashMap<>();
    private Metric metric;
    @BeforeEach
    void prepare() throws InterruptedException {
        MetricContextBlockingQueue metricContextBlockingQueue = new MetricContextBlockingQueue();
        QueueMetricsConsumer consumer = new QueueMetricsConsumer(metricContextBlockingQueue, false);
        MonitoringService queueMonitoringProducerService = new QueueMonitoringProducerService(metricContextBlockingQueue, consumer);
        context = consumer.getContext();

        for (int i = 0; i < 100; i++) {
            queueMonitoringProducerService.incrementSuccess("metric", 1);
        }

        for (int i = 0; i < 100; i++) {
            queueMonitoringProducerService.incrementError("metric");
        }

        Assertions.assertEquals(context.size(), 0);

        queueMonitoringProducerService.startConsumer();

        sleep(1000);

        metric = context.get("metric");

    }
    @Test
    public void get_one_metric_from_map() {
        Assertions.assertEquals(context.size(), 1);
    }
    @Test
    public void get_correct_counter_invoke() {
        Assertions.assertEquals(metric.getCounterInvokes(), 200);
    }
    @Test
    public void get_correct_counter_errors() {
        Assertions.assertEquals(metric.getCounterErrors(), 100);
    }
    @Test
    public void get_correct_total_time() {
        Assertions.assertEquals(metric.getTotalTime(), 100);
    }
    @Test
    public void get_correct_average_time() {
        Assertions.assertEquals(metric.getAverageTime(), 1);
    }
}