package com.ercarts.springboot.demo.web.data;

import com.ercarts.springboot.demo.web.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author dkyryk
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}