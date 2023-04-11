package com.example.Restaurante.Pedidos.domain.service;

import com.example.Restaurante.Pedidos.domain.dto.SmsRequest;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.config.error.RestException;
import org.springframework.http.ResponseEntity;

public interface OrdersService {
    ResponseEntity<Void> sendMessage(SmsRequest smsRequest);

}
