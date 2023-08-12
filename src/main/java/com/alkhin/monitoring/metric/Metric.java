package com.alkhin.monitoring.metric;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Metric implements MetricMBean {
    private static final Logger log = LogManager.getLogger();
    private long counterInvokes = 0L;
    private long totalTime = 0L;
    private long counterErrors = 0L;

    public Metric(String metricName, boolean register) {
        try {
            if (register) {
                ObjectName objectName = new ObjectName(metricName);
                MBeanServer server = ManagementFactory.getPlatformMBeanServer();
                server.registerMBean(this, objectName);
            }
        } catch (MalformedObjectNameException | InstanceAlreadyExistsException |
                 MBeanRegistrationException | NotCompliantMBeanException e) {
            log.error("Error creating MBEAN for monitoring: {}", e.getMessage());
        }
    }

    public void incrementCounter() {
        counterInvokes++;
    }

    public void incrementCounterErrors() {
        counterErrors++;
    }

    public void addTime(long time){
        setTotalTime(time);
    }

    @Override
    public Long getCounterInvokes() {
        return counterInvokes;
    }

    @Override
    public Long getTotalTime() {
        return totalTime;
    }

    @Override
    public Long getAverageTime() {
        long averageTime = 0;
        if (counterInvokes != 0) {
            averageTime = Math.abs(totalTime / (counterInvokes - counterErrors));
        }
        return averageTime;
    }

    @Override
    public long getCounterErrors() {
        return counterErrors;
    }

    private void setTotalTime(Long time) {
        totalTime += time;
    }
}