package com.ucsal.repository;

import com.ucsal.model.RoomReservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
public interface RoomReservationRepository extends JpaRepository<RoomReservation, Long> {
    List<RoomReservation> findByRoomNameAndStartTimeBetween(String roomName, LocalDateTime startTime, LocalDateTime endTime);
}
