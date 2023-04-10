package com.example.Restaurante.Usuarios.web;

import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.Usuarios.domain.dto.CreateUserDTO;
import com.example.Restaurante.Usuarios.domain.dto.UserInfoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/users")
public interface UsersAPI {

    @GetMapping(value = "/infoUser/{userId}")
    ResponseEntity<UserInfoDTO> consultUserInfo(
            @PathVariable Integer userId
    ) throws RestException;

    @PostMapping(value = "/createOwner")
    ResponseEntity<Void> createOwner (
            @Valid @RequestBody CreateUserDTO userDTO
    ) throws RestException;

    @PostMapping(value = "/createEmployee")
    ResponseEntity<Void> createEmployee (
            @Valid @RequestBody CreateUserDTO userDTO
    ) throws RestException;

    @PostMapping(value = "/createClient")
    ResponseEntity<Void> createClient (
            @Valid @RequestBody CreateUserDTO userDTO
    ) throws RestException;
}
