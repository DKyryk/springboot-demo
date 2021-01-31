package com.ercarts.clock.error;

import lombok.Value;

/**
 * @author dkyryk
 */
@Value
public class ErrorResponse {

    String details;
    String errorCode;

}