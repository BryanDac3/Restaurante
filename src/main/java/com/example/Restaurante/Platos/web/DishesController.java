package com.example.Restaurante.Platos.web;

import com.example.Restaurante.Platos.domain.dto.*;
import com.example.Restaurante.Platos.domain.service.DishService;
import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class DishesController implements DishesAPI{
    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);
    @Autowired
    private Utils utils;

    @Autowired
    private DishService dishService;


    @Override
    public ResponseEntity<Void> createDish(CreateDishDTO dishDTO) throws RestException {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateDish(UpdateDishDTO dishDTO) throws RestException {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> activeDish(List<ActiveDishDTO> activeDishDTO) throws RestException {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<ListDishesDTO>> listDishesRestaurant(Integer restaurantId, Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<List<ListDishesDTO>> listDishesOwner(Pageable pageable) {
        return null;
    }

    @Override
    public ResponseEntity<List<CategoryInfoDTO>> listCategoryDishes(Pageable pageable) {
        return null;
    }


}
