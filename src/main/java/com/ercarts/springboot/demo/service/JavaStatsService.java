package com.ercarts.springboot.demo.service;

import com.ercarts.springboot.demo.model.JavaStats;
import org.springframework.stereotype.Service;

/**
 * @author dkyryk
 */
@Service
public class JavaStatsService {

    public JavaStats getStatistics() {
        Runtime runtime = Runtime.getRuntime();
        return JavaStats.builder()
                .processors(runtime.availableProcessors())
                .availableMemory(runtime.freeMemory())
                .maxMemory(runtime.maxMemory())
                .totalMemory(runtime.totalMemory())
                .build();
    }
}
