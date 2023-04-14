package com.example.Restaurante.pedidos.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
public class ListOrdersClientDTO implements Serializable {
    private static final long serialVersionUID = 2608168758106865379L;

    private Integer idOrder;
    private List<DishOrderInfoDTO> infoDish;
    private Integer totalPrice;
    private String state;
}
