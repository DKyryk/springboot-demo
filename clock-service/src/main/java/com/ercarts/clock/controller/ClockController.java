package com.ercarts.clock.controller;

import java.net.URI;
import java.util.List;

import com.ercarts.clock.domain.Clock;
import com.ercarts.clock.error.ClockNotFoundException;
import com.ercarts.clock.error.ErrorResponse;
import com.ercarts.clock.model.ClockRegistration;
import com.ercarts.clock.service.ClockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author dkyryk
 */
@RestController
@RequestMapping("/api/clocks")
@Slf4j
public class ClockController {

    private final ClockService clockService;

    public ClockController(ClockService clockService) {
        this.clockService = clockService;
    }

    @GetMapping
    public List<Clock> allClocks() {
        return clockService.getAllClocks();
    }

    @GetMapping("/{id}")
    public Clock clockById(@PathVariable Long id) {
        return clockService.getClock(id);
    }

    @PostMapping
    public ResponseEntity<Clock> registerClock(@RequestBody ClockRegistration clockRegistration) {
        Clock registeredClock = clockService.registerClock(clockRegistration);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(registeredClock.getId())
                .toUri();

        return ResponseEntity.created(location).body(registeredClock);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ClockNotFoundException.class)
    public ErrorResponse processNotFound(ClockNotFoundException e) {
        log.error("Clock not found", e);
        return new ErrorResponse(e.getMessage(), "CLOCK_NOT_FOUND");
    }
}