package com.example.Restaurante.emun;

import lombok.Getter;

@Getter
public enum OrderStateE {
    PENDING_ORDER(1, "PEN", "PENDIENTE"),
    PREPARATION_ORDER(2, "PRE", "EN_PREPARACION"),
    FINISH_ORDER(3, "LIS", "LISTO"),
    DELIVERY_ORDER(4, "ENT", "ENTREGADO"),
    CANCEL_ORDER(5, "CAN", "CANCELADO")
    ;

    private final Integer id;
    private final String value;
    private final String stateName;

    OrderStateE(final Integer pId, final String pValue, final String stateName) {
        this.id = pId;
        this.value = pValue;
        this.stateName = stateName;
    }
}
