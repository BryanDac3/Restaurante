package com.example.Restaurante.Platos.domain.service;

import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.Platos.domain.dto.ListDishesDTO;
import com.example.Restaurante.Platos.domain.dto.UpdateDishDTO;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.Platos.web.DishesAPI;
import com.example.Restaurante.config.error.RestException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class DishServiceImpl implements DishService {


    @Override
    public ResponseEntity<Void> createDish(MenuDishEntity dishEntity, String userRol) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateDish(MenuDishEntity dishEntity, String userRol) {
        return null;
    }

    @Override
    public ResponseEntity<Void> activeDish(Boolean active, String userRol) {
        return null;
    }

    @Override
    public List<MenuDishEntity> listDishesRestaurant(Integer restaurantId, String userRol) {
        return null;
    }

    @Override
    public List<MenuDishEntity> listDishesOwner(String userRol) {
        return null;
    }
}
