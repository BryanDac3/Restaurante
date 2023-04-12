package com.example.Restaurante.Pedidos.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
public class ListOrdersDTO implements Serializable {
    private static final long serialVersionUID = 7683057242956151344L;

    private Integer idOrder;
    private OrderStateDTO stateInfo;
    private List<DishOrderInfoDTO> infoDish;
}
