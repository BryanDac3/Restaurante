package com.example.Restaurante.Usuarios.domain.service;

import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<Void> createOwner(UserEntity user, String rolValue) throws RestException;
    ResponseEntity<Void> createEmployee(UserEntity user, Integer ownerId, String rolValue) throws RestException;
    ResponseEntity<Void> createClient(UserEntity user) throws RestException;

    UserEntity getUserInfo(Integer idUser) throws RestException;
}
