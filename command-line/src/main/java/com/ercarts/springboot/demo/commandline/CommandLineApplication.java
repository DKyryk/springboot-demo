package com.ercarts.springboot.demo.commandline;

import java.util.Optional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class CommandLineApplication {

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner commandLineRunner(RestTemplate restTemplate) {
        return args -> {
            log.info("Command line started");
            ResponseEntity<JavaStats> response = restTemplate
                    .exchange("http://localhost:8080/api/stats", HttpMethod.GET,
                            null, new ParameterizedTypeReference<JavaStats>() {
                            });
            Optional.ofNullable(response.getBody())
                    .ifPresent(value -> log.info("Response received {}", value));
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(CommandLineApplication.class, args);
    }

}
