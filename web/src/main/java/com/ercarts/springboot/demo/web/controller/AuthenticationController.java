package com.ercarts.springboot.demo.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dkyryk
 */
@Controller
public class AuthenticationController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}
