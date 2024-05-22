package com.controlesalas.service;


import com.controlesalas.dto.SalaDto;
import com.controlesalas.entity.Sala;
import com.controlesalas.enums.StatusSala;
import com.controlesalas.repository.SalaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {
    @Autowired
    SalaRepository salaRepository;
    private Sala sala;


    @PostMapping("/addSala")
    public String addSala(SalaDto salaDto) {
        try {
            sala = new Sala();
            sala.setnSala(salaDto.nomesala());
            sala.setstatusReservada(StatusSala.LIVRE);
            salaRepository.save(sala);
            /*implementar notificacao ao microNotification*/

            return "Sala cadastrada com sucesso!!";
        } catch (Exception e) {
            return "Problema na inserção da Sala: " + e.getMessage();

        }
    }

    @GetMapping("/availableSalas")
    public String getAvailableSala() {
        try {
            List<Sala> salasDisponiveis = salaRepository.findByStatusReservada(StatusSala.LIVRE);

            return salasDisponiveis.toString();
        } catch (Exception e) {
            return "Problema na listas de Salas: " + e.getMessage();
        }
    }

    @PatchMapping("/maintenceSala")
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
