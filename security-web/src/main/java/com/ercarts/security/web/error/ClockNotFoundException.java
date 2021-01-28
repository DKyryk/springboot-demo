package com.ercarts.security.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author dkyryk
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClockNotFoundException extends RuntimeException {

    public ClockNotFoundException(String message) {
        super(message);
    }
}