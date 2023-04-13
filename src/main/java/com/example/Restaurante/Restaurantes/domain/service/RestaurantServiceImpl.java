package com.example.Restaurante.Restaurantes.domain.service;

import com.example.Restaurante.Restaurantes.domain.repositorio.RestaurantsCrudRepository;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantsService {
    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

    @Autowired
    private Utils utils;
    @Autowired
    private RestaurantsCrudRepository restaurantsRepository;
    @Transactional
    @Override
    public ResponseEntity<Void> createRestaurant(RestaurantEntity restaurant, String rolValue, Integer ownerId) throws RestException {
        utils.validateUserRol(rolValue, RolE.ONWER_VALUE);

        Optional<RestaurantEntity> restaurantDB = restaurantsRepository.findRestaurantEntityByNit(restaurant.getNit());
        if(restaurantDB == null || !restaurantDB.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_RESTAURANT_ALREADY_EXIST);
        }
        Optional<RestaurantEntity> restaurantOwnerDB = restaurantsRepository.findRestaurantEntityByUserId(ownerId);
        if(!restaurantOwnerDB.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_OWNER_HAS_RESTAURANT);
        }

        RestaurantEntity newRestaurant = restaurantsRepository.save(restaurant);
        restaurantsRepository.updateRestaurantUser(newRestaurant.getId(), ownerId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public List<RestaurantEntity> listRestaurant(String rolValue, Pageable pageable) throws RestException{
        utils.validateUserRol(rolValue, RolE.CLIENT_VALUE);

        Page<RestaurantEntity> listRestaurant = restaurantsRepository.findRestaurantEntityWithPagination(pageable);
        return listRestaurant.getContent();
    }

    @Override
    public RestaurantEntity restaurantInfo(String rolValue, Integer restaurantId) throws RestException {
        utils.validateUserRol(rolValue, RolE.CLIENT_VALUE);
        Optional<RestaurantEntity> restaurant = restaurantsRepository.findRestaurantEntityById(restaurantId);
        if(restaurant == null || restaurant.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_RESTAURANT_NOT_EXIST);
        }
        return restaurant.get();
    }
}
