package com.reservasalas.repository;

import com.reservasalas.entity.Reserva;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    Optional<Reserva> findByRoomNameAndStartTimeAndEndTime(String roomName, LocalDateTime startTime, LocalDateTime endTime);


}
