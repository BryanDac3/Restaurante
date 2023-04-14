package com.example.Restaurante.utils;

import com.example.Restaurante.usuarios.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findOneByEmail(String email);
}
