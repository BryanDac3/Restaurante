package com.example.Restaurante.Platos.domain.service;

import com.example.Restaurante.Platos.domain.dto.ActiveDishDTO;
import com.example.Restaurante.Platos.domain.dto.ListDishesDTO;
import com.example.Restaurante.Platos.persistence.entity.MenuCategoryEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DishService {

    ResponseEntity<Void> createDish(MenuDishEntity dishEntity, String userRol);
    ResponseEntity<Void> updateDish(MenuDishEntity dishEntity, String userRol);
    ResponseEntity<Void> activeDish(List<ActiveDishDTO> activeDishDTO, String userRol);
    List<MenuDishEntity> listDishesRestaurant(Integer restaurantId, String userRol);
    List<MenuDishEntity> listDishesOwner(String userRol);
    List<MenuCategoryEntity> listDishCategory(String userRol);
}
