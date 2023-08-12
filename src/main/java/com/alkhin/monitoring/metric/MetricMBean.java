package com.alkhin.monitoring.metric;

public interface MetricMBean {
    Long getCounterInvokes();
    Long getTotalTime();
    Long getAverageTime();
    long getCounterErrors();
}
