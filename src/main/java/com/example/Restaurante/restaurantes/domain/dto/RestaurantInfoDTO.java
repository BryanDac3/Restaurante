package com.example.Restaurante.restaurantes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class RestaurantInfoDTO implements Serializable {
    private static final long serialVersionUID = 1787015463399684867L;

    private String name;
    private String urlLogo;
    private String description;
    private String direction;
    private String restaurantNIT;
    private String contactNumber;
}
