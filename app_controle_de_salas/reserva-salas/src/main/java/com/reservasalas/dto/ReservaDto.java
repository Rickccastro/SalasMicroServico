package com.reservasalas.dto;

import java.time.LocalDateTime;
import java.util.UUID;


public record ReservaDto(String roomName,
                         UUID idProfessor,
                         LocalDateTime startTime,
                         LocalDateTime endTime) {
}
