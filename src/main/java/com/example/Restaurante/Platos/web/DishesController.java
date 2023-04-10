package com.example.Restaurante.Platos.web;

import com.example.Restaurante.Platos.domain.dto.*;
import com.example.Restaurante.Platos.domain.service.DishService;
import com.example.Restaurante.Platos.persistence.entity.MenuCategoryEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.Platos.persistence.mapper.DishEntityToDishDTO;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.Usuarios.persistence.mapper.UserToUserDTO;
import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.config.security.UserDetailsImpl;
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
        if(dishDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        MenuDishEntity dishEntity = DishEntityToDishDTO.INSTANCE.createDishDTOToMenuDishEntity(dishDTO);
        dishService.createDish(dishEntity, userDetails.getRol().getValue(), userDetails.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateDish(UpdateDishDTO dishDTO) throws RestException {
        if(dishDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        MenuDishEntity dishEntity = DishEntityToDishDTO.INSTANCE.updateDishDTOToMenuDishEntity(dishDTO);
        dishService.updateDish(dishEntity, userDetails.getRol().getValue());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> activeDish(List<ActiveDishDTO> activeDishDTO) throws RestException {
        if(activeDishDTO == null || activeDishDTO.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        dishService.activeDish(activeDishDTO, userDetails.getRol().getValue());
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<List<ListDishesDTO>> listDishesRestaurant(Integer restaurantId, Pageable pageable) throws RestException {
        if(restaurantId == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        List<MenuDishEntity> dishesRestaurant = dishService.listDishesRestaurant(
                restaurantId, userDetails.getRol().getValue(), pageable);
        List<ListDishesDTO> dishesDTOS = DishEntityToDishDTO.INSTANCE.listDishEntityToListDishesDTO(dishesRestaurant);
        return ResponseEntity.ok().body(dishesDTOS);
    }

    @Override
    public ResponseEntity<List<ListDishesDTO>> listDishesOwner(Pageable pageable) throws RestException{
        UserDetailsImpl userDetails = utils.getUserInfo();
        List<MenuDishEntity> dishesRestaurant = dishService.listDishesOwner(
                userDetails.getId(), userDetails.getRol().getValue(), pageable);
        List<ListDishesDTO> dishesDTOS = DishEntityToDishDTO.INSTANCE.listDishEntityToListDishesDTO(dishesRestaurant);
        return ResponseEntity.ok().body(dishesDTOS);
    }

    @Override
    public ResponseEntity<List<CategoryInfoDTO>> listCategoryDishes() throws RestException{
        UserDetailsImpl userDetails = utils.getUserInfo();
        List<MenuCategoryEntity> categoryEntities = dishService.listDishCategory(userDetails.getRol().getValue());
        List<CategoryInfoDTO> dishesCategoryDTOS = DishEntityToDishDTO.INSTANCE.listMenuCategoryEntityToListCategoryInfoDTO(categoryEntities);
        return ResponseEntity.ok().body(dishesCategoryDTOS);
    }


}
