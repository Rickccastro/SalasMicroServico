package com.notificationsalas.service;

import com.notificationsalas.dto.NotificationDto;
import com.notificationsalas.entity.Notification;
import com.notificationsalas.repository.NotificationRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;
    private Notification notification;

    @PostMapping("/addNotification")
    public String addNotification(NotificationDto notificationDto) {
        try {
            notification = new Notification();
            notification.setRequestTime(LocalDateTime.now());
            notification.setNotification(notificationDto.notification());
            notification.setIdProfessor(notificationDto.idProfessor());

            notificationRepository.save(notification);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8185/getProfessorList"))
                    .GET()
                    .build();
            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());


            return "Notificação:"+ notification.getNotification() +" enviada para os professores: " + response.body();

        } catch (Exception e) {
            return "Notificação não cadastrada: " + e.getMessage();

        }
    }
}
