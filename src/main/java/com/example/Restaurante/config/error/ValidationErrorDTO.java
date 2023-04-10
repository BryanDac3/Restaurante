package com.example.Restaurante.config.error;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ValidationErrorDTO implements Serializable {
    private static final long serialVersionUID = 6495866646574855205L;

    private List<FieldErrorDTO> fieldError = new ArrayList<>();

    public void addFieldError(String path, String message){
        FieldErrorDTO error = new FieldErrorDTO(path, message);
        fieldError.add(error);
    }
}
