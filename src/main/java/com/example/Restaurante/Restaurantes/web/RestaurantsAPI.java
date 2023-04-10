package com.example.Restaurante.Restaurantes.web;

import com.example.Restaurante.Restaurantes.domain.dto.CreateRestaurantDTO;
import com.example.Restaurante.Restaurantes.domain.dto.ListRestaurantsDTO;
import com.example.Restaurante.Restaurantes.domain.dto.RestaurantInfoDTO;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/restaurant")
public interface RestaurantsAPI {

    @GetMapping(value = "/restaurantsList")
    ResponseEntity<List<ListRestaurantsDTO>> listRestaurants(
            Pageable pageable
    ) throws RestException;

    @GetMapping(value = "/{restaurantId}")
    ResponseEntity<RestaurantInfoDTO> restaurantInfo(
            @PathVariable() Integer restaurantId
    ) throws RestException;

    @PostMapping
    ResponseEntity<Void> createRestaurant(
            @Valid @RequestBody CreateRestaurantDTO restaurantDTO
            ) throws RestException;
}
