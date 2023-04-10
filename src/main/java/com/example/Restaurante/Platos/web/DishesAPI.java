package com.example.Restaurante.Platos.web;

import com.example.Restaurante.Platos.domain.dto.*;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping(value = "/activeDish")
    ResponseEntity<Void> activeDish(
            @Valid @RequestBody List<ActiveDishDTO> dishActive
    ) throws RestException;

    @GetMapping
    ResponseEntity<List<ListDishesDTO>> listDishesRestaurant(
            @RequestParam(required = true) Integer restaurantId,
            Pageable pageable
    );

    @GetMapping("/owner")
    ResponseEntity<List<ListDishesDTO>>  listDishesOwner(
            Pageable pageable
    );

    @GetMapping("/categories")
    ResponseEntity<List<CategoryInfoDTO>> listCategoryDishes(
            Pageable pageable
    );
}
