package com.ercarts.clock.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.ercarts.clock.domain.Clock;
import com.ercarts.clock.error.ClockAppException;
import com.ercarts.clock.error.ErrorResponse;
import com.ercarts.clock.model.ClockRegistration;
import com.ercarts.clock.service.ClockService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @ExceptionHandler(ClockAppException.class)
    @ResponseBody
    public ResponseEntity<ErrorResponse> handleException(HttpServletResponse resp, ClockAppException e) {
        log.error("Error during processing", e);
        resp.setStatus(e.getRawStatusCode());
        return new ResponseEntity<>(new ErrorResponse(e.getReason(), e.getErrorCode()), e.getStatus());
    }
}