package com.example.Restaurante.Platos.web;

import com.example.Restaurante.Platos.domain.dto.*;
import com.example.Restaurante.config.error.RestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(value = "/dishes")
public interface DishesAPI {

    @Operation(summary = "Crea nuevo plato en el restaurante, " +
            "para realizarlo el usuario debe tener el rol de Propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    ResponseEntity<Void> createDish(
            @Valid @RequestBody CreateDishDTO dishDTO
            ) throws RestException;

    @Operation(summary = "Modifica la informacion de un plato del restaurante del propietario, " +
            "para realizarlo el usuario debe tener el rol de Propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Acepted"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping
    ResponseEntity<Void> updateDish(
            @Valid @RequestBody UpdateDishDTO dishDTO
            ) throws RestException;

    @Operation(summary = "Activa o desactiva un plato del restaurante del propietario para que no se muestre en la lista, " +
            "para realizarlo el usuario debe tener el rol de Propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Acepted"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PutMapping(value = "/activeDish")
    ResponseEntity<Void> activeDish(
            @Valid @RequestBody List<ActiveDishDTO> dishActive
    ) throws RestException;

    @Operation(summary = "Lista los platos disponibles paginados en el restaurante dado, " +
            "para realizarlo el usuario debe tener el rol de Cliente")
    @GetMapping
    ResponseEntity<List<ListDishesDTO>> listDishesRestaurant(
            @RequestParam(required = true) Integer restaurantId,
            Pageable pageable
    ) throws RestException;

    @Operation(summary = "Lista la informacion de todos los platos paginados en el restaurante del propietario, " +
            "para realizarlo el usuario debe tener el rol de Propietario")
    @GetMapping("/owner")
    ResponseEntity<List<ListDishesDTO>>  listDishesOwner(
            Pageable pageable
    ) throws RestException;

    @Operation(summary = "Lista las categorias disponibles en las que puede ser un plato, " +
            "para realizarlo el usuario debe tener el rol de Propietario")
    @GetMapping("/categories")
    ResponseEntity<List<CategoryInfoDTO>> listCategoryDishes() throws RestException;
}
