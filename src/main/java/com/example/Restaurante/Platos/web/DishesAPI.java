package com.example.Restaurante.Platos.web;

import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.Platos.domain.dto.ListDishesDTO;
import com.example.Restaurante.Platos.domain.dto.UpdateDishDTO;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/dishes")
public interface DishesAPI {

    @PostMapping
    ResponseEntity<Void> createDish(
            @Valid @RequestBody CreateDishDTO dishDTO
            ) throws RestException;

    @PutMapping
    ResponseEntity<Void> updateDish(
            @Valid @RequestBody UpdateDishDTO dishDTO
            ) throws RestException;

    @PutMapping(value = "/activeDish/{active}")
    ResponseEntity<Void> activeDish(
            @PathVariable Boolean active
    ) throws RestException;

    @GetMapping
    ResponseEntity<ListDishesDTO> listDishesRestaurant(
            @RequestParam(required = true) Integer restaurantId
    );
}
