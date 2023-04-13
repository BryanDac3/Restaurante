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
import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.emun.RestExceptionE;
import com.example.Restaurante.emun.RolE;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DishServiceImpl implements DishService {

    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

    private final static Integer ACTIVE_DISH = 1;
    private final static Integer INACTIVE_DISH = 0;

    @Autowired
    private Utils utils;
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private DishCategoryRepository dishCategoryRepository;

    @Override
    @Transactional
    public ResponseEntity<Void> createDish(MenuDishEntity newDish, String userRol, Integer ownerId) throws RestException {
        utils.validateUserRol(userRol, RolE.ONWER_VALUE);
        Optional<Integer> restaurantId = dishRepository.findRestaurantIdByOwnerId(ownerId);
        if(restaurantId.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_OWNER_NOT_RESTAURANT);
        }
        Optional<MenuCategoryEntity> categoryDB = dishCategoryRepository.findMenuCategoryEntityById(newDish.getCategory().getId());
        if(categoryDB.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_CATEGORY_NOT_EXIST);
        }
        Optional<MenuDishEntity> dishDB = dishRepository.findMenuDishEntityByNameAndRestaurantId(newDish.getName(), restaurantId.get());
        if(!dishDB.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_DISH_EXIST);
        }
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(restaurantId.get());
        newDish.setRestaurant(restaurant);
        newDish.setActive(ACTIVE_DISH);
        dishRepository.save(newDish);
        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public ResponseEntity<Void> updateDish(MenuDishEntity updateDish, String userRol) throws RestException {
        utils.validateUserRol(userRol, RolE.ONWER_VALUE);
        validateDishExist(updateDish.getId());
        dishRepository.updateMenuDish(
                updateDish.getId(),
                updateDish.getDescription(),
                updateDish.getPrice()
        );
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> activeDish(List<ActiveDishDTO> activeDishDTO, String userRol) throws RestException {
        utils.validateUserRol(userRol, RolE.ONWER_VALUE);
        for(ActiveDishDTO dish: activeDishDTO){
            validateDishExist(dish.getDishId());
            dishRepository.activeMenuDish(dish.getDishId(), dish.getActive() ? ACTIVE_DISH : INACTIVE_DISH);
        }
        return null;
    }

    private void validateDishExist(Integer dishId) throws RestException {
        Optional<MenuDishEntity> dishDB = dishRepository.findMenuDishEntityById(dishId);
        if(dishDB.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_DISH_NOT_EXIST);
        }
    }

    @Override
    public List<MenuDishEntity> listDishesRestaurant(Integer restaurantId, String userRol, Pageable pageable) throws RestException {
        utils.validateUserRol(userRol, RolE.CLIENT_VALUE);
        Optional<Integer> restaurantIdDb = dishRepository.findRestaurantById(restaurantId);
        if(restaurantIdDb.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_RESTAURANT_NOT_EXIST);
        }
        Page<MenuDishEntity> dishesRestaurant = dishRepository.findMenuDishEntityActiveByRestaurantIdWithPagination(restaurantId, pageable);
        return dishesRestaurant.getContent();
    }

    @Override
    public List<MenuDishEntity> listDishesOwner(Integer ownerId, String userRol, Pageable pageable) throws RestException {
        utils.validateUserRol(userRol, RolE.ONWER_VALUE);
        Optional<Integer> restaurantId = dishRepository.findRestaurantIdByOwnerId(ownerId);
        if(restaurantId.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_OWNER_NOT_RESTAURANT);
        }
        Page<MenuDishEntity> dishesRestaurant = dishRepository.findMenuDishEntityByRestaurantIdWithPagination(restaurantId.get(), pageable);
        return dishesRestaurant.getContent();
    }

    @Override
    public List<MenuCategoryEntity> listDishCategory(String userRol) throws RestException {
        utils.validateUserRol(userRol, RolE.ONWER_VALUE);
        List<MenuCategoryEntity> dishCategory = dishCategoryRepository.findAllMenuCategoryEntity();
        return dishCategory;
    }
}
