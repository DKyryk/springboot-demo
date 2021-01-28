package com.ercarts.security.web.domain;

import lombok.Builder;
import lombok.Value;

/**
 * @author dkyryk
 */
@Value
@Builder
public class Clock {
    long id;
    String company;
    String colour;
    ClockType clockType;
}