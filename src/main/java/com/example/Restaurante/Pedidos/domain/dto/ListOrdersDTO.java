package com.example.Restaurante.Pedidos.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Data
public class ListOrdersDTO implements Serializable {
    private static final long serialVersionUID = 7683057242956151344L;

    private Integer idOrder;
    private String nameClient;
    private DishOrderInfoDTO infoDish;
}
