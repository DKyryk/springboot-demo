package com.ercarts.springboot.demo.controller;

import com.ercarts.springboot.demo.model.JavaStats;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dkyryk
 */
@Controller
@RequestMapping("/stats")
public class JavaStatsController {

    public static final String STATISTICS_VIEW_PAGE = "statistics";

    @GetMapping
    public String calculateStats(Model model) {
        Runtime runtime = Runtime.getRuntime();
        model.addAttribute("stats", JavaStats.builder()
                .processors(runtime.availableProcessors())
                .availableMemory(runtime.freeMemory())
                .maxMemory(runtime.maxMemory())
                .totalMemory(runtime.totalMemory())
                .build());
        return STATISTICS_VIEW_PAGE;
    }
}
