package com.example.Restaurante.Restaurantes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class CreateRestaurantDTO implements Serializable {
    private static final long serialVersionUID = 1723377081889371999L;

    @NotNull
    @Pattern(regexp = "(?!^\\d+$)^.+$")
    private String name;

    @NotNull
    @Pattern(regexp = "[0-9]+$")
    private String restaurantNIT;

    @NotNull
    private String direction;

    @NotNull
    @Pattern(regexp = "(\\+[0-9]{2})+[0-9]{10}$")
    private String contactNumber;

    @NotNull
    private String urlLogo;

    private String description;

}
