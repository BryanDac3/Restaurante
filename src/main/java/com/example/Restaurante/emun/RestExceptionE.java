package com.example.Restaurante.emun;

import org.springframework.http.HttpStatus;

public enum RestExceptionE {
    ERROR_USER_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "User.not.exist"),
    ERROR_USER_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "User.already.exist"),
    ERROR_USER_EMAIL_ALREADY_EXIST(1002, HttpStatus.BAD_REQUEST, "User.Email.already.exist"),
    ERROR_OWNER_NOT_RESTAURANT(1003, HttpStatus.BAD_REQUEST, "Onwer.not.restaurant"),
    ERROR_RESTAURANT_ALREADY_EXIST(1004, HttpStatus.BAD_REQUEST, "Restaurant.already.exist"),
    ERROR_OWNER_HAS_RESTAURANT(1005, HttpStatus.BAD_REQUEST, "Owner.Has.Restaurant"),
    ERROR_RESTAURANT_NOT_EXIST(1005, HttpStatus.BAD_REQUEST, "Restaurant.not.exist"),
    UNAUTHORIZED(4001, HttpStatus.UNAUTHORIZED, "User.unauthorized"),
    ;

    private final Integer id;
    private final HttpStatus httpStatus;
    private final String message;

    RestExceptionE(final Integer pId, final HttpStatus pHttpStatus, final String pMessage) {
        this.id = pId;
        this.httpStatus = pHttpStatus;
        this.message = pMessage;
    }

    public Integer getId() {
        return id;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public String getMessage() {
        return message;
    }
}
