package com.example.Restaurante.restaurantes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ListRestaurantCategoriesDTO implements Serializable {
    private static final long serialVersionUID = 827539087260108026L;

    private String name;
    private Integer idCategory;
}
