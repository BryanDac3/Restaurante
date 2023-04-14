package com.example.Restaurante.platos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class UpdateDishDTO implements Serializable {
    private static final long serialVersionUID = 7567611389971500001L;

    @NotNull
    private Integer id;

    @NotNull
    @Min(value = 0)
    private Integer price;

    @NotNull
    private String description;
}
