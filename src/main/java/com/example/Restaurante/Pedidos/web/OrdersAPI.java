package com.example.Restaurante.Pedidos.web;

import com.example.Restaurante.Pedidos.domain.dto.CreateOrderDTO;
import com.example.Restaurante.Pedidos.domain.dto.ListOrdersDTO;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.config.error.RestException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/orders")
public interface OrdersAPI {

    @Operation(summary = "Servicio que realiza la orden del cliente, " +
            "para realizarlo el usuario debe tener el rol de Cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Created"),
            @ApiResponse(responseCode = "404", description = "Not found")
    })
    @PostMapping
    ResponseEntity<Void> makeOrder(
            @Valid @RequestBody List<CreateOrderDTO> ordersDTO
    ) throws RestException;

    @Operation(summary = "Lista las ordenes pendientes o que pertenezcan al empleado, " +
            "filtrado por el estado de la orden, " +
            "para realizarlo el usuario debe tener el rol de Empleado")
    @GetMapping(value = "/employee")
    ResponseEntity<List<ListOrdersDTO>> listOrderEmployee(
            @RequestParam(required = false) String orderStateValue,
            Pageable pageable
    ) throws RestException;

    @Operation(summary = "Servicio que permite al empleado cambiar la orden a En Preparacion, " +
            "la orden debe estar en el estado Pendiente, " +
            "para realizarlo el usuario debe tener el rol de Empleado")
    @GetMapping(value = "/employee/prepareOrder/{orderId}")
    ResponseEntity<Void> prepareOrderEmployee(
            @PathVariable(value = "Identificador de la orden") Integer orderId
    ) throws RestException;

    @Operation(summary = "Servicio que permite al empleado cambiar la orden a Finalizado, " +
            "la orden debe estar en el estado En Preparacion, " +
            "para realizarlo el usuario debe tener el rol de Empleado")
    @GetMapping(value = "/employee/finishOrder/{orderId}")
    ResponseEntity<Void> finishOrderEmployee(
            @PathVariable(value = "Identificador de la orden") Integer orderId
    ) throws RestException;

    @Operation(summary = "Servicio que permite al empleado cambiar la orden a Entregado, " +
            "la orden debe estar en el estado Finalizado, " +
            "para realizarlo el usuario debe tener el rol de Empleado")
    @GetMapping(value = "/employee/deliverOrder/{orderId}")
    ResponseEntity<Void> deliverOrderEmployee(
            @RequestParam(required = true) String pin,
            @PathVariable(value = "Identificador de la orden") Integer orderId
    ) throws RestException;

    @Operation(summary = "Servicio que permite al cliente cancelar una orden, " +
            "la orden debe estar en el estado Pendiente, " +
            "para realizarlo el usuario debe tener el rol de Cliente")
    @GetMapping(value = "/client/cancelOrder/{orderId}")
    ResponseEntity<Void> cancelOrderClient(
            @PathVariable(name = "Identificador de la orden") Integer orderId
    ) throws RestException;

    @Operation(summary = "Lista todas las ordenes del cliente, filtrado por el estado de la orden" +
            "para realizarlo el usuario debe tener el rol de Cliente")
    @GetMapping(value = "/client")
    ResponseEntity<List<ListOrdersDTO>> listOrderClient(
            @RequestParam(required = false) String orderStateValue,
            Pageable pageable
    ) throws RestException;
}
