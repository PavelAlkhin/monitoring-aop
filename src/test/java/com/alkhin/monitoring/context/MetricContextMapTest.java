package com.alkhin.monitoring.context;


import com.alkhin.monitoring.metric.Metric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MetricContextMapTest {

    MetricContextConcurrentMap metricContextConcurrentMap;

    private Metric metric;

    @BeforeEach
    void prepare() {

        metricContextConcurrentMap = new MetricContextConcurrentMap(false);

        String METRIC_NAME = "METRIC";
        for (int i = 0; i < 100; i++)
            metricContextConcurrentMap.incrementSuccess(METRIC_NAME, 1);

        for (int i = 0; i < 100; i++)
            metricContextConcurrentMap.incrementError(METRIC_NAME);


        metric = metricContextConcurrentMap.getMetric(METRIC_NAME);

    }

    @Test
    public void testIncrementSuccess() {
        Assertions.assertEquals(metric.getCounterInvokes() - metric.getCounterErrors(), 100L);
        Assertions.assertEquals(metric.getTotalTime(), 100);
    }

    @Test
    public void testIncrementError() {
        Assertions.assertEquals(metric.getCounterErrors(), 100);
    }

    @Test
    public void testGetMetric() {
        Assertions.assertEquals(metricContextConcurrentMap.getContext().size(), 1);
    }
}