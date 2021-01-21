package com.ercarts.springboot.demo.web.service;

import java.util.List;
import java.util.Optional;

import com.ercarts.springboot.demo.web.data.RoomRepository;
import com.ercarts.springboot.demo.web.model.Room;
import org.springframework.stereotype.Service;

/**
 * @author dkyryk
 */
@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getRooms() {
        return roomRepository.findAll();
    }

    public Optional<Room> getById(long id) {
        return roomRepository.findById(id);
    }
}