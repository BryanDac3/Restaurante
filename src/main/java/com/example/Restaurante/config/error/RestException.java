package com.example.Restaurante.config.error;

import com.example.Restaurante.emun.RestExceptionE;

public class RestException extends Exception{
    private final RestExceptionE info;

    public RestException(RestExceptionE pInfo) {
        super();
        this.info = pInfo;
    }

    public RestExceptionE getInfo() {
        return info;
    }
}
