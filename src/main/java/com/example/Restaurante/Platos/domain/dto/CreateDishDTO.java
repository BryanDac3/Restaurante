package com.example.Restaurante.Platos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class CreateDishDTO implements Serializable {
    private static final long serialVersionUID = -82369409894936318L;

    @NotBlank
    private String name;

    @NotNull
    @Min(value = 0)
    private Integer price;


    private String description;

    @NotBlank
    private String urlImgDish;

    @NotNull
    private Integer categoryId;


}
