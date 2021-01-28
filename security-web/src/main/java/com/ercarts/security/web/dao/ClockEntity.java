package com.ercarts.security.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ercarts.security.web.domain.ClockType;
import lombok.Data;

/**
 * @author dkyryk
 */
@Entity
@Table(name="CLOCK")
@Data
public class ClockEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CLOCK_ID")
    private long id;

    @Column(name="MANUFACTURER")
    private String manufacturer;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private ClockType clockType;

    @Column(name="COLOUR")
    private String colour;
}