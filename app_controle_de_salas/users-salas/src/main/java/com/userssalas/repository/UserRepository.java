package com.userssalas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.userssalas.entity.Usuario;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Usuario, UUID> {

}
