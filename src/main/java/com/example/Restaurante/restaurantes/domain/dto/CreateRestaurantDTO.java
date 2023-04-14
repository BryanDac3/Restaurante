package com.example.Restaurante.restaurantes.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Getter
@Setter
public class CreateRestaurantDTO implements Serializable {
    private static final long serialVersionUID = 1723377081889371999L;

    @NotBlank
    @Pattern(regexp = "(?!^\\d+$)^.+$")
    private String name;

    @NotBlank
    @Pattern(regexp = "[0-9]+$")
    private String restaurantNIT;

    @NotBlank
    private String direction;

    @NotBlank
    @Pattern(regexp = "(\\+[0-9]{2})+[0-9]{10}$")
    private String contactNumber;

    @NotBlank
    private String urlLogo;

    private String description;

}
