package com.example.Restaurante.Pedidos.domain.service;

import com.example.Restaurante.Pedidos.domain.dto.SmsRequest;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.config.error.RestException;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrdersService {
    ResponseEntity<Void> makeOrder(
            List<OrderEntity> orderEntities,
            Integer clientId,
            String rolValue
    ) throws RestException;
    ResponseEntity<Void> listOrderEmployee(
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
    ResponseEntity<Void> infoClientOrder(
            Integer clientId,
            String rolValue
    ) throws RestException;
    ResponseEntity<Void> cancelOrderClient(
            Integer orderId,
            Integer clientId,
            String rolValue
    ) throws RestException;
    ResponseEntity<Void> sendMessage(SmsRequest smsRequest);

}
