package com.example.Restaurante.Pedidos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class OrderStateDTO implements Serializable {
    private static final long serialVersionUID = -5055620757469235423L;

    private Integer id;
    private String name;
    private String value;
}
