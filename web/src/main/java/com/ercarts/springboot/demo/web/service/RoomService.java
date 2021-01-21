package com.ercarts.springboot.demo.web.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ercarts.springboot.demo.web.model.Room;
import org.springframework.stereotype.Service;

/**
 * @author dkyryk
 */
@Service
public class RoomService {

    private static final List<Room> ROOMS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Room(i, "#" + i, "Room at " + i, (i + 5) + " free"))
            .collect(Collectors.toList());

    public List<Room> getRooms() {
        return ROOMS;
    }
}
