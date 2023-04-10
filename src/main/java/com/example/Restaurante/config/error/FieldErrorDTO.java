package com.example.Restaurante.config.error;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class FieldErrorDTO implements Serializable {
    private static final long serialVersionUID = -990383687794092305L;

    private String field;
    private String message;

    public FieldErrorDTO(String field, String message){
        this.field = field;
        this.message = message;
    }
}
