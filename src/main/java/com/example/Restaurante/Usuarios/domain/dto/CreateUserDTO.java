package com.example.Restaurante.Usuarios.domain.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;
import java.io.Serializable;

@Getter
@Setter
public class CreateUserDTO implements Serializable {
    private static final long serialVersionUID = 3151600367315730704L;

    @NotNull
    private String names;

    @NotNull
    private String lastNames;

    @NotNull
    @Pattern(regexp = "[0-9]+$")
    private String userDNI;

    @NotNull
    @Pattern(regexp = "(\\+[0-9]{2})+[0-9]{10}$")
    private String cellNumber;

    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-z]+\\.[a-z]{2,3}")
    private String email;

    @NotNull
    private String password;

    private Integer idRol;

    private Integer idRestaurant;
}
