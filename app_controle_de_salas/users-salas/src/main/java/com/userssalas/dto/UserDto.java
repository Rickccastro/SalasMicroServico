package com.userssalas.dto;

import com.userssalas.enums.RoleUser;

public record UserDto(
        String nome,
        RoleUser role
) {
}
