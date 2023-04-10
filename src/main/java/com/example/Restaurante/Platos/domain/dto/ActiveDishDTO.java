package com.example.Restaurante.Platos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
public class ActiveDishDTO implements Serializable {
    private static final long serialVersionUID = 2495898507172837041L;

    @NotNull
    private Integer dishId;

    @NotNull
    private Boolean active;
}
