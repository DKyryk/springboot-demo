package com.ercarts.springboot.demo.web.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dkyryk
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {

    private long id;
    private String number;
    private String name;
    private String info;
}
