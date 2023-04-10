package com.example.Restaurante.Restaurantes.domain.service;

import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RestaurantsService {
    ResponseEntity<Void> createRestaurant(RestaurantEntity restaurant, String rolValue, Integer ownerId) throws RestException;
    List<RestaurantEntity> listRestaurant(String rolValue, Pageable pageable) throws RestException;
    RestaurantEntity restaurantInfo(String rolValue, Integer restaurantId) throws RestException;
}
