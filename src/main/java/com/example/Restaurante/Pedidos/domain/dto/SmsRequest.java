package com.example.Restaurante.Pedidos.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class SmsRequest implements Serializable {
    private static final long serialVersionUID = 8626795379929970702L;

    private final String phoneNumber;

    private final String message;

    public SmsRequest(String phoneNumber, String message){
        this.message = message;
        this.phoneNumber = phoneNumber;
    }
}
