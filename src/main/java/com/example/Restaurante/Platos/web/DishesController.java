package com.example.Restaurante.Platos.web;

import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.Platos.domain.dto.ListDishesDTO;
import com.example.Restaurante.Platos.domain.dto.UpdateDishDTO;
import com.example.Restaurante.config.error.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class DishesController implements DishesAPI{
    @Override
    public ResponseEntity<Void> createDish(CreateDishDTO dishDTO) throws RestException {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> updateDish(UpdateDishDTO dishDTO) throws RestException {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<Void> activeDish(Boolean active) throws RestException {
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<ListDishesDTO> listDishesRestaurant(Integer restaurantId) {
        return null;
    }
}
