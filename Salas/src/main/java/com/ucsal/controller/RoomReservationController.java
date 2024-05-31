package com.ucsal.controller;

import com.ucsal.model.RoomReservation;
import com.ucsal.service.RoomReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/reservations")
public class RoomReservationController {

    @Autowired
    private RoomReservationService service;

    @PostMapping("/reserve")
    public RoomReservation reserveRoom(@RequestParam String roomName, @RequestParam String reservedBy,
                                       @RequestParam String startTime, @RequestParam String endTime) {
        LocalDateTime start = LocalDateTime.parse(startTime);
        LocalDateTime end = LocalDateTime.parse(endTime);

        return service.reserveRoom(roomName, reservedBy, start, end);
    }
}
