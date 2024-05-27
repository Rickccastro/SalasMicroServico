package com.ucsal.service;


import com.ucsal.model.RoomReservation;
import com.ucsal.repository.RoomReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RoomReservationService {

    @Autowired
    private RoomReservationRepository repository;

    public RoomReservation reserveRoom(String roomName, String reservedBy, LocalDateTime startTime, LocalDateTime endTime) {
        List<RoomReservation> reservations = repository.findByRoomNameAndStartTimeBetween(roomName, startTime, endTime);
        if (!reservations.isEmpty()) {
            throw new RuntimeException("Room is not available for the requested time slot");
        }

        RoomReservation reservation = new RoomReservation();
        reservation.setRoomName(roomName);
        reservation.setReservedBy(reservedBy);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setStatus("Reserved");

        return repository.save(reservation);
    }
}
