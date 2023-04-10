package com.example.Restaurante.Platos.domain.service;

import com.example.Restaurante.Platos.domain.dto.ActiveDishDTO;
import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.Platos.domain.dto.ListDishesDTO;
import com.example.Restaurante.Platos.domain.dto.UpdateDishDTO;
import com.example.Restaurante.Platos.domain.repositorio.DishCategoryRepository;
import com.example.Restaurante.Platos.domain.repositorio.DishRepository;
import com.example.Restaurante.Platos.persistence.entity.MenuCategoryEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.Platos.web.DishesAPI;
import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

    @Autowired
    private Utils utils;
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishCategoryRepository dishCategoryRepository;

    @Override
    public ResponseEntity<Void> createDish(MenuDishEntity dishEntity, String userRol) {
        return null;
    }

    @Override
    public ResponseEntity<Void> updateDish(MenuDishEntity dishEntity, String userRol) {
        return null;
    }

    @Override
    public ResponseEntity<Void> activeDish(List<ActiveDishDTO> activeDishDTO, String userRol) {
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

    @Override
    public List<MenuCategoryEntity> listDishCategory(String userRol) {
        return null;
    }
}
