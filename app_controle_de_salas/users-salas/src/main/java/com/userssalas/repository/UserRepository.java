package com.userssalas.repository;

import com.userssalas.enums.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import com.userssalas.entity.Usuario;

import java.util.List;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Usuario, UUID> {
        List<Usuario> findByRole(RoleUser roleUser);
}
