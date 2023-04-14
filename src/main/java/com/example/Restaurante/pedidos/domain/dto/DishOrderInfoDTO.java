package com.example.Restaurante.pedidos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class DishOrderInfoDTO implements Serializable {
    private static final long serialVersionUID = 3836687240075095900L;

    private String dishName;
    private Integer amount;
}
