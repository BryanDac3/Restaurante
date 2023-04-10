package com.example.Restaurante.Restaurantes.web;

import com.example.Restaurante.Restaurantes.domain.dto.CreateRestaurantDTO;
import com.example.Restaurante.Restaurantes.domain.dto.ListRestaurantsDTO;
import com.example.Restaurante.Restaurantes.domain.dto.RestaurantInfoDTO;
import com.example.Restaurante.Restaurantes.domain.service.RestaurantsService;
import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.example.Restaurante.Restaurantes.persistence.mapper.RestaurantEntityToRestaurantDTO;
import com.example.Restaurante.Restaurantes.persistence.mapper.RestaurantEntityToRestaurantDTOImpl;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.config.security.UserDetailsImpl;
import com.example.Restaurante.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class RestaurantsController implements RestaurantsAPI {

    @Autowired
    private Utils utils;

    @Autowired
    private RestaurantsService restaurantsService;
    @Override
    public ResponseEntity<List<ListRestaurantsDTO>> listRestaurants(Pageable pageable) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        List<RestaurantEntity> listRestaurants = restaurantsService.listRestaurant(
                userDetails.getRol().getValue(), pageable);
        List<ListRestaurantsDTO> listRestaurantsDTOS = RestaurantEntityToRestaurantDTO.INSTANCE.restaurantEntityToListRestaurantsDTO(listRestaurants);
        return ResponseEntity.ok().body(listRestaurantsDTOS);
    }

    @Override
    public ResponseEntity<RestaurantInfoDTO> restaurantInfo(Integer restaurantId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        RestaurantEntity restaurantInfo = restaurantsService.restaurantInfo(
                userDetails.getRol().getValue(), restaurantId);
        RestaurantInfoDTO restaurantInfoDTO =
                RestaurantEntityToRestaurantDTO.INSTANCE.restaurantEntityToRestaurantInfoDTO(restaurantInfo);
        return ResponseEntity.ok().body(restaurantInfoDTO);
    }

    @Override
    public ResponseEntity<Void> createRestaurant(CreateRestaurantDTO restaurantDTO) throws RestException {
        if(restaurantDTO == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        UserDetailsImpl userDetails = utils.getUserInfo();
        RestaurantEntity restaurant =
                RestaurantEntityToRestaurantDTOImpl.INSTANCE.createRestaurantDTOToRestaurantEntity(restaurantDTO);
        ResponseEntity<Void> response = restaurantsService.createRestaurant(
                restaurant, userDetails.getRol().getValue(), userDetails.getId()
        );
        return response;
    }
}
