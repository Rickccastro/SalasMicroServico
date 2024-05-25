package com.userssalas.entity;

import java.util.UUID;

import com.userssalas.enums.RoleUser;
import jakarta.persistence.*;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;
    private String nome ;
    @Enumerated(EnumType.STRING)
    private RoleUser role ;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UUID getId() {
        return id;
    }
    public void setId(UUID id) {
        this.id = id;
    }

    public RoleUser getRole() {
        return role;
    }

    public void setRole(RoleUser role) {
        this.role = role;
    }
}