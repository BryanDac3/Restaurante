package com.example.Restaurante.usuarios.domain.service;

import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.emun.RolE;
import com.example.Restaurante.usuarios.domain.repositorio.UserCrudRepository;
import com.example.Restaurante.usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class UserServiceImplTest {

    @Mock
    private UserCrudRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private Utils utils;

    @InjectMocks
    private UserServiceImpl userService;

    private UserEntity user;
    private int updateTable;
    private int restaurantId;
    private int userId;
    private String userRol;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        user = new UserEntity();
        user.setNames("Name test");
        user.setLastNames("Last name test");
        user.setPassword("12345678");
        user.setEmail("email@email.com");
        user.setIdNumber("12345678");

    }

    @Test
    void createOwner() throws RestException {
        when(userRepository.saveUser(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyInt()
        )).thenReturn(updateTable);
        assertEquals(
                userService.createOwner(user, userRol).getStatusCode(),
                HttpStatus.CREATED
        );
    }

    @Test
    void createEmployee() throws RestException {
        when(userRepository.findRestaurantIdByUserId(anyInt())).thenReturn(Optional.of(restaurantId));
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        assertEquals(
                userService.createEmployee(user, userId, userRol).getStatusCode(),
                HttpStatus.CREATED
        );

        when(userRepository.findRestaurantIdByUserId(anyInt())).thenReturn(Optional.empty());
        RestException exception = assertThrows(RestException.class, () ->{
            userService.createEmployee(user, userId, userRol);
        });
        assertEquals("Owner.not.restaurant", exception.getInfo().getMessage());
    }

    @Test
    void createClient() throws RestException {
        when(userRepository.saveUser(
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyString(),
                anyInt()
        )).thenReturn(updateTable);
        assertEquals(
                userService.createClient(user).getStatusCode(),
                HttpStatus.CREATED
        );
    }
}