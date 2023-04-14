package com.example.Restaurante.usuarios.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserRolDTO implements Serializable {
    private static final long serialVersionUID = -7921349738463165071L;

    private  Integer id;

    private String name;

    private String value;
}
