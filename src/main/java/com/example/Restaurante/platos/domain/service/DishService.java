package com.example.Restaurante.platos.domain.service;

import com.example.Restaurante.platos.domain.dto.ActiveDishDTO;
import com.example.Restaurante.platos.persistence.entity.MenuCategoryEntity;
import com.example.Restaurante.platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishService {

    ResponseEntity<Void> createDish(MenuDishEntity dishEntity, String userRol, Integer ownerId) throws RestException;
    ResponseEntity<Void> updateDish(MenuDishEntity dishEntity, String userRol) throws RestException;
    ResponseEntity<Void> activeDish(List<ActiveDishDTO> activeDishDTO, String userRol) throws RestException;
    List<MenuDishEntity> listDishesRestaurant(Integer restaurantId, String userRol, Pageable pageable) throws RestException;
    List<MenuDishEntity> listDishesOwner(Integer ownerId, String userRol, Pageable pageable) throws RestException;
    List<MenuCategoryEntity> listDishCategory(String userRol) throws RestException;
}
