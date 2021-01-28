package com.ercarts.security.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ercarts.security.web.domain.Clock;
import com.ercarts.security.web.error.ClockNotFoundException;
import com.ercarts.security.web.repository.ClockRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dkyryk
 */
@RestController
@RequestMapping("/api/clocks")
@Slf4j
public class ClockController {

    private final ClockRepository clockRepository;

    public ClockController(ClockRepository clockRepository) {
        this.clockRepository = clockRepository;
    }

    @GetMapping
    public List<Clock> allClocks() {
        return clockRepository.findAll()
                .stream()
                .map(Clock::fromEntity)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Clock clockById(@PathVariable Long id) {
        return clockRepository.findById(id)
                .map(Clock::fromEntity)
                .orElseThrow(() -> new ClockNotFoundException("Clock with id is not registered: " + id));
    }

}