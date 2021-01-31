package com.ercarts.clock.error;

/**
 * @author dkyryk
 */
public class ClockNotFoundException extends RuntimeException {

    public ClockNotFoundException(String message) {
        super(message);
    }
}