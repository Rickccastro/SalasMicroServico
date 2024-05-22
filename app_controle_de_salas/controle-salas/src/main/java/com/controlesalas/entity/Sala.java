package com.controlesalas.entity;


import com.controlesalas.enums.StatusSala;
import jakarta.persistence.*;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private UUID id;
    private String nsala;
    @Enumerated(EnumType.STRING)
    private StatusSala statusReservada;
    private String dataReserva;

    public String getnSala() {
        return nsala;
    }

    public void setnSala(String nsala) {
        this.nsala = nsala;
    }

    public StatusSala getstatusReservada() {
        return statusReservada;
    }

    public void setstatusReservada(StatusSala statusReservada) {
        this.statusReservada = statusReservada;
    }

    public void setDataReserva(String dataReserva) {
        this.dataReserva = dataReserva;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
    @Override
    public String toString() {
        return "Sala" + nsala + ", statusReservada=" + statusReservada ;
    }
}
