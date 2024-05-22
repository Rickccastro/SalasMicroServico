package com.controlesalas.repository;


import com.controlesalas.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import com.controlesalas.enums.StatusSala;


@Repository
public interface SalaRepository extends JpaRepository< Sala, UUID> {
    List<Sala> findByStatusReservada(StatusSala statusReservada);
    Optional<Sala> findByNsala(String nsala);

}
