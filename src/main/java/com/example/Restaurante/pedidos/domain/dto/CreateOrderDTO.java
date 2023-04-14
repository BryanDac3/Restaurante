package com.example.Restaurante.pedidos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CreateOrderDTO implements Serializable {
    private static final long serialVersionUID = 4014289277201345539L;

    private Integer dishId;

    private Integer amount;
}
