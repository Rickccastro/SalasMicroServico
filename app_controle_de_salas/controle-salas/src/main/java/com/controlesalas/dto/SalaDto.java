package com.controlesalas.dto;


import java.util.UUID;

public record SalaDto(String nomesala,
                      String dataReserva,
                      UUID idProfessor
                      ) {
}
