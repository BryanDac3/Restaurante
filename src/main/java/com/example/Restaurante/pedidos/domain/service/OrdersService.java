package com.example.Restaurante.pedidos.domain.service;

import com.example.Restaurante.pedidos.persistence.entity.OrderDishEntity;
import com.example.Restaurante.pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrdersService {
    ResponseEntity<Void> makeOrder(
            List<OrderDishEntity> orderEntities,
            Integer clientId,
            String rolValue
    ) throws RestException;
    List<OrderEntity>  listOrderEmployee(
            String orderStateValue,
            Integer employeeId,
            String rolValue,
            Pageable pageable
    ) throws RestException;
    ResponseEntity<Void> prepareOrderEmployee(
            Integer orderId,
            Integer employeeId,
            String rolValue
    ) throws RestException;
    ResponseEntity<Void> finishOrderEmployee(
            Integer orderId,
            Integer employeeId,
            String rolValue
    ) throws RestException;
    ResponseEntity<Void> deliverOrderEmployee(
            Integer orderId,
            String pin,
            Integer employeeId,
            String rolValue
    ) throws RestException;
    ResponseEntity<Void> cancelOrderClient(
            Integer orderId,
            Integer clientId,
            String rolValue
    ) throws RestException;

    List<OrderEntity>  listOrderClient(
            String orderStateValue,
            Integer clientId,
            String rolValue,
            Pageable pageable
    ) throws RestException;

}
