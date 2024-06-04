package com.reservasalas.service.Exception;

public class ReservaExistenteException extends RuntimeException {
    public ReservaExistenteException(String message) {
        super(message);
    }
}