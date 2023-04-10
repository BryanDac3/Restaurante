package com.example.Restaurante.emun;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum RolE {
    ADMIN_VALUE(1, "ADM", "ADMINISTRADOR"),
    EMPLOYEE_VALUE(2, "EMP", "EMPLEADO"),
    CLIENT_VALUE(3, "CLI", "CLIENTE"),
    ONWER_VALUE(4, "PRO", "PROPIETARIO")
    ;

    private final Integer id;
    private final String value;
    private final String rolName;
    
    RolE(final Integer pId, final String pValue, final String pRolName) {
        this.id = pId;
        this.value = pValue;
        this.rolName = pRolName;
    }
}
