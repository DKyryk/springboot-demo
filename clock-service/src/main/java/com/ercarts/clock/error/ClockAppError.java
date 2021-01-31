package com.ercarts.clock.error;

import org.springframework.http.HttpStatus;

/**
 * @author dkyryk
 */
public enum ClockAppError {

    CLOCK_NOT_FOUND(HttpStatus.NOT_FOUND, "Clock #%d is not registered");

    ClockAppError(HttpStatus httpStatus, String template) {
        this.httpStatus = httpStatus;
        this.template = template;
    }

    private final HttpStatus httpStatus;
    private final String template;

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ClockAppException toException(Throwable cause, Object... parameters) {
        String message = String.format(this.template, parameters);
        return new ClockAppException(this, message, cause);
    }

    public ClockAppException toException(Object... parameters) {
        return toException(null, parameters);
    }
}