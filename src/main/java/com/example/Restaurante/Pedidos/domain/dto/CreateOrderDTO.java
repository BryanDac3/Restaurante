package com.example.Restaurante.Pedidos.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class CreateOrderDTO implements Serializable {
    private static final long serialVersionUID = 4014289277201345539L;

    private Integer dishId;

    private Integer amount;
}
