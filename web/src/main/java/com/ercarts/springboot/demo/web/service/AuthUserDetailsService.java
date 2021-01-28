package com.ercarts.springboot.demo.web.service;

import java.util.Optional;

import com.ercarts.springboot.demo.web.data.UserRepository;
import com.ercarts.springboot.demo.web.model.User;
import com.ercarts.springboot.demo.web.model.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author dkyryk
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public AuthUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = Optional.ofNullable(userRepository.findByUsername(username))
                .orElseThrow(() -> new UsernameNotFoundException("Bad username: " + username));
        return new UserPrincipal(user);
    }
}