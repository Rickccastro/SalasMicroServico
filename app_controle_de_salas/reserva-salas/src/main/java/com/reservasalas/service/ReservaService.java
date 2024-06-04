package com.reservasalas.service;

import com.reservasalas.dto.ReservaDto;
import com.reservasalas.entity.Reserva;
import com.reservasalas.repository.ReservaRepository;
import com.reservasalas.service.Exception.ReservaExistenteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public String reserveRoom(ReservaDto reservaDto) {
        Reserva reserva;
        try {
            reserva = createReserva(reservaDto);
            reservaRepository.save(reserva);
        } catch (ReservaExistenteException e) {
            return e.getMessage();
        }

        String notificationResponse = sendNotification(reserva);
        String statusResponse = changeRoomStatus(reserva);

        return notificationResponse + "\n" + statusResponse;
    }

    private Reserva createReserva(ReservaDto reservaDto) throws ReservaExistenteException {
        Optional<Reserva> existingReserva = reservaRepository.findByRoomNameAndStartTimeAndEndTime(
                reservaDto.roomName(), reservaDto.startTime(), reservaDto.endTime());

        if (existingReserva.isPresent()) {
            Reserva reservaExistente = existingReserva.get();
            sendExistingNotification(reservaExistente);
            throw new ReservaExistenteException("Reserva já existente para a sala: " + reservaExistente.getRoomName());
        }

        Reserva reserva = new Reserva();
        reserva.setRoomName(reservaDto.roomName());
        reserva.setIdProfessor(reservaDto.idProfessor());
        reserva.setStartTime(reservaDto.startTime());
        reserva.setEndTime(reservaDto.endTime());

        return reserva;
    }

    private void sendExistingNotification(Reserva reserva) {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            String requestBody = String.format(
                    "{\"notification\": \"Sala já reservada: %s\", \"idProfessor\": \"%s\"}",
                    reserva.getRoomName(), reserva.getIdProfessor()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8184/addNotification"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            // Log error or handle accordingly
        }
    }

    private String sendNotification(Reserva reserva) {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            String requestBody = String.format(
                    "{\"notification\": \"Nova Sala Criada: %s\", \"idProfessor\": \"%s\"}",
                    reserva.getRoomName(), reserva.getIdProfessor()
            );

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8184/addNotification"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (java.net.ConnectException e) {
            return "Falha na notificação: " + reserva.getRoomName() + " notificação não enviada. O serviço de notificação não está disponível.";
        } catch (Exception e) {
            return "Erro ao enviar notificação: " + e.getMessage();
        }
    }

    private String changeRoomStatus(Reserva reserva) {
        try {
            HttpClient client = HttpClient.newBuilder().build();
            String requestBody = String.format("{\"roomName\": \"%s\"}", reserva.getRoomName());

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(new URI("http://localhost:8182/changeStatusSala"))
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                    .header("Content-Type", "application/json")
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (java.net.ConnectException e) {
            return "Falha no controle: " + reserva.getRoomName() + " controle de status não atualizado. O serviço de controle de status não está disponível.";
        } catch (Exception e) {
            return "Erro ao atualizar status da sala: " + e.getMessage();
        }
    }
}
