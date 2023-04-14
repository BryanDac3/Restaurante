package com.example.Restaurante.Usuarios.web;

import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.Usuarios.domain.dto.CreateUserDTO;
import com.example.Restaurante.Usuarios.domain.dto.UserInfoDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(value = "/users")
public interface UsersAPI {

    @Operation(summary = "Consulta la informacion de usuario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserInfoDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content) })
    @GetMapping(value = "/infoUser/{userId}")
    ResponseEntity<UserInfoDTO> consultUserInfo(
            @PathVariable(name = "Identificador del usuario") Integer userId
    ) throws RestException;

    @Operation(summary = "Crea un propietario de restaurante, " +
            "para realizarlo el usuario dede tener el rol de administrador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping(value = "/createOwner")
    ResponseEntity<Void> createOwner (
            @Valid @RequestBody CreateUserDTO userDTO
    ) throws RestException;

    @Operation(summary = "Crea un empleado del restaurante, " +
            "para realizarlo el usuario dede tener el rol de propietario")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping(value = "/createEmployee", consumes = "application/json")
    ResponseEntity<Void> createEmployee (
            @Valid @RequestBody CreateUserDTO userDTO
    ) throws RestException;

    @Operation(summary = "Crea un cliente de la aplicacion")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping(value = "/createClient")
    ResponseEntity<Void> createClient (
            @Valid @RequestBody CreateUserDTO userDTO
    ) throws RestException;
}
