package com.controlesalas.service;


import com.controlesalas.dto.SalaDto;
import com.controlesalas.entity.Sala;
import com.controlesalas.enums.StatusSala;
import com.controlesalas.repository.SalaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    @Autowired
    SalaRepository salaRepository;
    private Sala sala;


    public String addSala(SalaDto salaDto) {
        try {
            sala = new Sala();
            sala.setnSala(salaDto.nomesala());
            sala.setId(salaDto.idProfessor());
            sala.setstatusReservada(StatusSala.LIVRE);
            salaRepository.save(sala);

            String requestBody = "{\"message\": \"Nova Sala Criada: " + sala.getnSala() + "\", \"professorId\": \"" + sala.getId().toString() + "\"}";

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8184/addNotification"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());

            return "Novas salas cadastradas: " + sala.getnSala() + " resposta: " + response.body().toString();

        } catch (java.net.ConnectException e) {
            return "Novas salas cadastradas: " + sala.getnSala() + " notificação não enviada. O serviço de notificação não está disponível.";

        } catch (Exception e) {
            return "Problema na inserção da Sala: " + e.getMessage();
        }
    }


    public String getStatusSalas(String nsala) {
        try {
            Optional<Sala> salaOptional = salaRepository.findByNsala(nsala);
            if (salaOptional.isPresent()) {
                sala = salaOptional.get();
                sala.getstatusReservada();

                return "Status sala: " + sala.getstatusReservada();
            } else {
                return "Sala não encontrada!";
            }
        } catch (Exception e) {
            return "Problema em informar o status da Sala: " + e.getMessage();
        }
    }


    public String getAvailableSala() {
        try {
            List<Sala> salasDisponiveis = salaRepository.findByStatusReservada(StatusSala.LIVRE);

            return salasDisponiveis.toString();
        } catch (Exception e) {
            return "Problema na listas de Salas: " + e.getMessage();
        }
    }


    public String maintenceSala(SalaDto salaDto) {
        try {
            Optional<Sala> salaOptional = salaRepository.findByNsala(salaDto.nomesala());
            if (salaOptional.isPresent()) {
                sala = salaOptional.get();
                sala.setstatusReservada(StatusSala.MANUTENCAO);
                sala.setDataReserva(salaDto.dataReserva());
                salaRepository.save(sala);
                return "Sala " + sala.getnSala() + " em manutenção!! até " + salaDto.dataReserva();
            } else {
                return "Sala não encontrada!";
            }
        } catch (Exception e) {
            return "Problema na atualização da Sala: " + e.getMessage();
        }
    }

}
