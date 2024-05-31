package com.notificationsalas.controller;


import com.netflix.discovery.EurekaClient;
import com.notificationsalas.dto.NotificationDto;
import com.notificationsalas.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    EurekaClient eurekaClient;
    @Autowired
    NotificationService notificationService;

    @PostMapping("/addNotification")
    public String addNotification(@RequestBody NotificationDto notificationDto) {

        return notificationService.addNotification(notificationDto);
    }
}
