package com.example.Restaurante.Platos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class CreateDishDTO implements Serializable {
    private static final long serialVersionUID = -82369409894936318L;

    @NotNull
    private String name;

    @NotNull
    @Min(value = 0)
    private Integer price;


    private String description;

    @NotNull
    private String urlImgDish;

    @NotNull
    private String category;


}
