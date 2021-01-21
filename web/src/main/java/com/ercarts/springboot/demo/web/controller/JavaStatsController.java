package com.ercarts.springboot.demo.web.controller;

import com.ercarts.springboot.demo.web.service.JavaStatsService;
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

    private final JavaStatsService javaStatsService;

    public JavaStatsController(JavaStatsService javaStatsService) {
        this.javaStatsService = javaStatsService;
    }

    @GetMapping
    public String calculateStats(Model model) {
        model.addAttribute("stats", javaStatsService.getStatistics());
        return STATISTICS_VIEW_PAGE;
    }
}
