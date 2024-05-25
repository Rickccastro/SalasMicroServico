package com.userssalas.service;

import com.userssalas.dto.UserDto;
import com.userssalas.entity.Usuario;
import com.userssalas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    private Usuario user;

    @PostMapping("/createUser")
    public String createUser(UserDto userDto) {
        try {
            user = new Usuario();
            user.setNome(userDto.nome());
            user.setRole(userDto.role());
            userRepository.save(user);
            return "Usuario cadastrado com sucesso!";
        }
        catch (Exception e) {
          return "Problema no cadastro" + e.getMessage();
        }

    }

}
