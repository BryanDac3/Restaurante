package com.example.Restaurante.Pedidos.domain.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Data
public class CreateOrderDTO implements Serializable {
    private static final long serialVersionUID = 4014289277201345539L;

    @NotNull
    private Integer idOrder;

    @NotNull
    @Min(value = 0)
    private Integer amount;
}
