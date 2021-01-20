package com.ercarts.springboot.demo.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.ercarts.springboot.demo.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author dkyryk
 */
@Controller
@RequestMapping("/rooms")
public class RoomController {

    private static final String ROOMS_VIEW = "rooms";

    private static final List<Room> ROOMS = IntStream.rangeClosed(1, 10)
            .mapToObj(i -> new Room(i, "#" + i, "Room at " + i, (i + 5) + " free"))
            .collect(Collectors.toList());

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", ROOMS);
        return ROOMS_VIEW;
    }
}
