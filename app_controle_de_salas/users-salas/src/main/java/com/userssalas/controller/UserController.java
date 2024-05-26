package com.userssalas.controller;

import com.netflix.discovery.EurekaClient;

import com.userssalas.dto.UserDto;
import com.userssalas.entity.Usuario;
import com.userssalas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private EurekaClient eurekaClient;
    @Autowired
    private UserService userService;


    @PostMapping("/createUser")
    public String createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping ("/getProfessorList")
    public List<Usuario> getProfessorList() {
        return userService.getProfessorList();
    }
}
