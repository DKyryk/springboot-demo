package com.ercarts.springboot.demo.web.controller;

import com.ercarts.springboot.demo.web.service.RoomService;
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

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getRooms());
        return ROOMS_VIEW;
    }
}
