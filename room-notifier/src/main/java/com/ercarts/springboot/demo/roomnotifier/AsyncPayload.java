package com.ercarts.springboot.demo.roomnotifier;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dkyryk
 */
@Data
@AllArgsConstructor
public class AsyncPayload {
    long id;
    String model;
}