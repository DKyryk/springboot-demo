package com.ercarts.clock;

import java.util.List;

import com.ercarts.clock.domain.Clock;
import com.ercarts.clock.domain.ClockType;
import com.ercarts.clock.model.ClockRegistration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author dkyryk
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntTestClockApp {

    @Autowired
    private TestRestTemplate restTemplate;

    private ClockClient clockClient;

    @BeforeEach
    void setUp() {
        clockClient = new ClockClient(restTemplate);
    }

    @Test
    void predefinedClocksLoaded() {
        ResponseEntity<List<Clock>> response = clockClient.getAllClocks();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        List<Clock> clocks = response.getBody();
        assertThat(clocks).isNotNull();
        assertThat(clocks).isNotEmpty();
        assertThat(clocks).hasSizeGreaterThanOrEqualTo(7);
    }

    @Test
    void predefinedClockFound() {
        ResponseEntity<Clock> response = clockClient.getClock(1);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Clock clock = response.getBody();
        assertThat(clock).isNotNull();
        assertThat(clock.getClockType()).isEqualTo(ClockType.QUARTZ);
        assertThat(clock.getCompany()).isEqualTo("EuropeanStandardClocks");
        assertThat(clock.getColour()).isEqualTo("Black");
    }

    @Test
    void absentClockNotFound() {
        ResponseEntity<Clock> response = clockClient.getClock(25);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    void clockRegistered() {
        ClockRegistration registration = new ClockRegistration();
        registration.setColour("Purple");
        registration.setTypeLabel("M");
        registration.setCompany("NewBrand");
        ResponseEntity<Clock> registrationResult = clockClient.registerClock(registration);
        assertThat(registrationResult.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Clock registeredClock = registrationResult.getBody();
        assertThat(registeredClock).isNotNull();

        ResponseEntity<Clock> response = clockClient.getClock(registeredClock.getId());
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        Clock clock = response.getBody();
        assertThat(clock).isNotNull();
        assertThat(clock.getClockType()).isEqualTo(ClockType.MECHANICAL);
        assertThat(clock.getCompany()).isEqualTo(registration.getCompany());
        assertThat(clock.getColour()).isEqualTo(registration.getColour());
    }
}