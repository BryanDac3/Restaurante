package com.example.Restaurante.Usuarios.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class UserInfoDTO implements Serializable {

    private static final long serialVersionUID = 5502139432976408578L;

    private String names;

    private String lastNames;

    private String userDNI;

    private String cellNumber;

    private String email;

    private UserRolDTO userRol;
}
