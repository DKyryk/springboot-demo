package com.ercarts.springboot.demo.roomnotifier;

import java.util.Random;
import java.util.stream.IntStream;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import static com.ercarts.springboot.demo.roomnotifier.QueueParams.EXCHANGE_NAME;
import static com.ercarts.springboot.demo.roomnotifier.QueueParams.ROUTING_KEY;

/**
 * @author dkyryk
 */
@Component
@Slf4j
public class MessagePublisher implements CommandLineRunner {

    private static final Random RNG = new Random();
    private static final int ROOMS_IN_DB_COUNT = 28;

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper mapper;

    @Autowired
    private ConfigurableApplicationContext context;

    public MessagePublisher(RabbitTemplate rabbitTemplate, ObjectMapper mapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapper = mapper;
    }

    @Override
    public void run(String... args) throws Exception {
        try (ConfigurableApplicationContext c = context) {
            int index = RNG.nextInt(ROOMS_IN_DB_COUNT) + 1;
            AsyncPayload payload = new AsyncPayload(index, "ROOM");
            rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, mapper.writeValueAsString(payload));
        }
    }
}