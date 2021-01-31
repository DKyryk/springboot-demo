package com.ercarts.clock.error;

import lombok.Getter;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author dkyryk
 */
@Getter
public class ClockAppException extends ResponseStatusException {

    private final String errorCode;

    public ClockAppException(ClockAppError error, String reason, Throwable cause) {
        super(error.getHttpStatus(), reason, cause);
        this.errorCode = error.name();
    }
}