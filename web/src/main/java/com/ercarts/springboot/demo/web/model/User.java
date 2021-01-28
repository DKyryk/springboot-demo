package com.ercarts.springboot.demo.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author dkyryk
 */
@Entity
@Table(name = "USER")
@Data
public class User {
    @Id
    @Column(name = "USER_ID")
    private long id;

    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;
}