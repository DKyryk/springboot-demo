package com.ercarts.clock;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author dkyryk
 */
@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Clock application",
                description = "Simple clock application to try out Spring Boot features",
                version = "1.0"
        )
)
public class ClockApp {

    public static void main(String[] args) {
        SpringApplication.run(ClockApp.class, args);
    }
}