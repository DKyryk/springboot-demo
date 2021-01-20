package com.ercarts.springboot.demo.controller;

import com.ercarts.springboot.demo.model.JavaStats;
import com.ercarts.springboot.demo.service.JavaStatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dkyryk
 */
@RestController
@RequestMapping("/api/stats")
public class JavaStatsApi {

    private final JavaStatsService javaStatsService;

    public JavaStatsApi(JavaStatsService javaStatsService) {
        this.javaStatsService = javaStatsService;
    }

    @GetMapping
    public JavaStats calcStats() {
        return javaStatsService.getStatistics();
    }
}
