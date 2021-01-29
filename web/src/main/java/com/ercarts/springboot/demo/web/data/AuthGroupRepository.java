package com.ercarts.springboot.demo.web.data;

import java.util.List;

import com.ercarts.springboot.demo.web.model.AuthGroup;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dkyryk
 */
public interface AuthGroupRepository extends JpaRepository<AuthGroup, Long> {

    List<AuthGroup> findByUsername(String username);
}