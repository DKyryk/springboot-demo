package com.ercarts.springboot.demo.web.async;

import com.ercarts.springboot.demo.web.service.RoomService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author dkyryk
 */
@Component
@Slf4j
public class RoomListener {

    private final ObjectMapper mapper;
    private final RoomService roomService;

    public RoomListener(ObjectMapper objectMapper, RoomService roomService) {
        this.mapper = objectMapper;
        this.roomService = roomService;
    }

    public void receiveMessage(String message) {
        try {
            AsyncPayload payload = mapper.readValue(message, AsyncPayload.class);
            if ("ROOM".equals(payload.getModel())) {
                roomService.getById(payload.getId())
                        .ifPresent(room ->
                                log.info("Room {} #{} queried asynchronously", room.getName(), room.getNumber()));
            } else {
                log.warn("Unknown message type {}", payload.getModel());
            }
        } catch (Exception e) {
            log.error("Deserialization failure");
        }
    }
}