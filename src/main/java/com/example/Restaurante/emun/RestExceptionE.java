package com.example.Restaurante.emun;

import org.springframework.http.HttpStatus;

public enum RestExceptionE {
    ERROR_USER_NOT_FOUND(1000, HttpStatus.NOT_FOUND, "User.not.exist"),
    ERROR_USER_ALREADY_EXIST(1001, HttpStatus.BAD_REQUEST, "User.already.exist"),
    ERROR_USER_EMAIL_ALREADY_EXIST(1002, HttpStatus.BAD_REQUEST, "User.Email.already.exist"),
    ERROR_OWNER_NOT_RESTAURANT(1003, HttpStatus.BAD_REQUEST, "Owner.not.restaurant"),
    ERROR_RESTAURANT_ALREADY_EXIST(1004, HttpStatus.BAD_REQUEST, "Restaurant.already.exist"),
    ERROR_OWNER_HAS_RESTAURANT(1005, HttpStatus.BAD_REQUEST, "Owner.Has.Restaurant"),
    ERROR_RESTAURANT_NOT_EXIST(1006, HttpStatus.BAD_REQUEST, "Restaurant.not.exist"),
    ERROR_DISH_EXIST(1007, HttpStatus.BAD_REQUEST, "Dish.already.exist"),
    ERROR_DISH_NOT_EXIST(1008, HttpStatus.BAD_REQUEST, "Dish.not.exist"),
    ERROR_CATEGORY_NOT_EXIST(1009, HttpStatus.BAD_REQUEST, "Category.not.exist"),
    ERROR_AMOUNT_ORDER(1010, HttpStatus.BAD_REQUEST, "Amount.order"),
    ERROR_DISH_ORDER(1011, HttpStatus.BAD_REQUEST, "Dish.order"),
    ERROR_ORDER_DISH_NOT_SAME_RESTAURANT(1012, HttpStatus.BAD_REQUEST, "Dish.not.same.restaurant"),
    ERROR_ORDER_DUPLICATE_DISH(1013, HttpStatus.BAD_REQUEST, "Order.duplicate.dish"),
    ERROR_ORDER_STATE_NOT_EXIST(1014, HttpStatus.BAD_REQUEST, "Order.state.not.exist"),
    ERROR_ORDER_NOT_EXIST(1015, HttpStatus.BAD_REQUEST, "Order.not.exist"),
    ERROR_ORDER_EMPLOYEE_NOT_SAME_RESTAURANT(1016, HttpStatus.BAD_REQUEST, "Employee.not.same.restaurant"),
    ERROR_ORDER_NOT_PENDING(1017, HttpStatus.BAD_REQUEST, "Order.not.pending"),
    ERROR_ORDER_NOT_PREPARING(1018, HttpStatus.BAD_REQUEST, "Order.not.preparing"),
    ERROR_ORDER_NOT_FINISH(1019, HttpStatus.BAD_REQUEST, "Order.not.finish"),
    ERROR_ORDER_CANCEL(1020, HttpStatus.BAD_REQUEST, "Order.cancel"),
    ERROR_SEND_SMS(1021, HttpStatus.BAD_REQUEST, "Order.not.send.SMS"),
    ERROR_ORDER_PIN_NOT_VALID(1022, HttpStatus.BAD_REQUEST, "Order.pin.not.valid"),
    ERROR_ORDER_CANNOT_CANCEL(1023, HttpStatus.BAD_REQUEST, "Order.cannot.cancel"),
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
