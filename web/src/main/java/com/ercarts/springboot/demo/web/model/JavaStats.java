package com.ercarts.springboot.demo.web.model;

import lombok.Builder;
import lombok.Value;

/**
 * @author dkyryk
 */
@Value
@Builder
public class JavaStats {
    int processors;
    long totalMemory;
    long availableMemory;
    long maxMemory;
}
