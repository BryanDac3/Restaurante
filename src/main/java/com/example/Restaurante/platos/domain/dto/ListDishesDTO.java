package com.example.Restaurante.platos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ListDishesDTO implements Serializable {
    private static final long serialVersionUID = 8317347753710004752L;

    private Integer id;
    private String name;
    private Integer price;
    private String description;
    private String urlImgDish;
    private CategoryInfoDTO category;
    private Integer active;
}
