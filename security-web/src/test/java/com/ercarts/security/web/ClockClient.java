package com.ercarts.security.web;

import java.util.List;

import com.ercarts.security.web.domain.Clock;
import com.ercarts.security.web.model.ClockRegistration;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

/**
 * @author dkyryk
 */
public class ClockClient {

    private final TestRestTemplate restTemplate;

    public ClockClient(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<Clock>> getAllClocks() {
        return restTemplate.exchange("/api/clocks", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Clock>>() {
                });
    }

    public ResponseEntity<Clock> getClock(long id) {
        return restTemplate.getForEntity("/api/clocks/{id}", Clock.class, id);
    }

    public ResponseEntity<Clock> registerClock(ClockRegistration clockRegistration) {
        return restTemplate.postForEntity("/api/clocks", clockRegistration, Clock.class);
    }
}