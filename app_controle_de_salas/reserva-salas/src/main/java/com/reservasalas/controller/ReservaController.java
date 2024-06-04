package com.reservasalas.controller;

import com.reservasalas.dto.ReservaDto;

import com.reservasalas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReservaController {
    @Autowired
    private ReservaService service;

    @PostMapping("/reserve")
    public String reserveRoom(@RequestBody ReservaDto reservaDto) {
        return service.reserveRoom(reservaDto);
    }
}
