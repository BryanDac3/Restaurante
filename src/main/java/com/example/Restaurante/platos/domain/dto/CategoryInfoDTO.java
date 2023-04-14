package com.example.Restaurante.platos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CategoryInfoDTO implements Serializable {
    private static final long serialVersionUID = 8759637607343470566L;

    private Integer id;
    private String name;
    private String value;
}
