package com.example.Restaurante.restaurantes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ListRestaurantsDTO implements Serializable {
    private static final long serialVersionUID = -8995538593564060406L;

    private Integer id;
    private String name;
    private String urlLogo;
    private String description;
}
