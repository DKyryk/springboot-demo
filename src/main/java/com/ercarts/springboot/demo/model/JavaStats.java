package com.ercarts.springboot.demo.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author dkyryk
 */
@Data
@Builder
public class JavaStats {
    int processors;
    long totalMemory;
    long availableMemory;
    long maxMemory;
}
