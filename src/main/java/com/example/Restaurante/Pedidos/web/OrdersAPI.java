package com.example.Restaurante.Pedidos.web;

import com.example.Restaurante.Pedidos.domain.dto.CreateOrderDTO;
import com.example.Restaurante.Pedidos.domain.dto.ListOrdersDTO;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/orders")
public interface OrdersAPI {

    @PostMapping
    ResponseEntity<Void> makeOrder(
            @Valid @RequestBody List<CreateOrderDTO> ordersDTO
    ) throws RestException;

    @GetMapping(value = "/employee")
    ResponseEntity<List<ListOrdersDTO>> listOrderEmployee(
            @RequestParam(required = false) String orderStateValue,
            Pageable pageable
    ) throws RestException;

    @GetMapping(value = "/employee/prepareOrder/{orderId}")
    ResponseEntity<Void> prepareOrderEmployee(
            @PathVariable Integer orderId
    ) throws RestException;

    @GetMapping(value = "/employee/finishOrder/{orderId}")
    ResponseEntity<Void> finishOrderEmployee(
            @PathVariable Integer orderId
    ) throws RestException;

    @GetMapping(value = "/employee/deliverOrder/{orderId}")
    ResponseEntity<Void> deliverOrderEmployee(
            @RequestParam(required = true) String pin,
            @PathVariable Integer orderId
    ) throws RestException;

    @GetMapping(value = "/client/cancelOrder/{orderId}")
    ResponseEntity<Void> cancelOrderClient(
            @PathVariable Integer orderId
    ) throws RestException;

    @GetMapping(value = "/client")
    ResponseEntity<ListOrdersDTO> listOrderClient(
            @RequestParam(required = false) String orderStateValue,
            Pageable pageable
    ) throws RestException;
}
