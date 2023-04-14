package com.example.Restaurante.restaurantes.web;

import com.example.Restaurante.restaurantes.domain.dto.CreateRestaurantDTO;
import com.example.Restaurante.restaurantes.domain.dto.ListRestaurantsDTO;
import com.example.Restaurante.restaurantes.domain.dto.RestaurantInfoDTO;
import com.example.Restaurante.config.error.RestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/restaurant")
public interface RestaurantsAPI {

    @Operation(summary = "Lista todos los restaurantes paginados, " +
            "para realizarlo el usuario debe tener el rol de Cliente")
    @GetMapping(value = "/restaurantsList")
    ResponseEntity<List<ListRestaurantsDTO>> listRestaurants(
            Pageable pageable
    ) throws RestException;

    @Operation(summary = "Muestra la informacion detallada del restaurante, " +
            "para realizarlo el usuario debe tener el rol de Cliente")
    @GetMapping(value = "/{restaurantId}")
    ResponseEntity<RestaurantInfoDTO> restaurantInfo(
            @PathVariable(value = "Identificador del restaurante", required = true) Integer restaurantId
    ) throws RestException;

    @Operation(summary = "Crea un restaurante en la aplicacion, " +
            "para realizarlo el usuario debe tener el rol de Propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    ResponseEntity<Void> createRestaurant(
            @Valid @RequestBody CreateRestaurantDTO restaurantDTO
            ) throws RestException;
}
