package com.ercarts.springboot.demo.web.controller;

import java.util.Collections;
import java.util.List;

import com.ercarts.springboot.demo.web.model.Room;
import com.ercarts.springboot.demo.web.service.RoomService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getAllRooms(Model model) {
        model.addAttribute("rooms", roomService.getRooms());
        return ROOMS_VIEW;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String get(@PathVariable long id, Model model) {
        List<Room> rooms = roomService.getById(id).map(Collections::singletonList)
                .orElse(Collections.emptyList());
        model.addAttribute("rooms", rooms);
        return ROOMS_VIEW;
    }
}