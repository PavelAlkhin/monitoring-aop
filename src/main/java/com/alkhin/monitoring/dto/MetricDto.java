package com.alkhin.monitoring.dto;

public record MetricDto (String name, boolean invoke, boolean error, long time) {}
