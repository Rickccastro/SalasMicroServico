package com.reservasalas.entity;

import com.reservasalas.enums.StatusSala;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;


@Entity
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String roomName;
    private UUID idProfessor;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    @Enumerated(EnumType.STRING)
    private StatusSala status;

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getId() {
        return id;
    }

    public UUID getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(UUID idProfessor) {
        this.idProfessor = idProfessor;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusSala getStatus() {
        return status;
    }

    public void setStatus(StatusSala status) {
        this.status = status;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public void setstatusReservada(StatusSala status) {
        this.status = status;
    }

    public StatusSala getstatusReservada() {
        return status;
    }



}
