package com.example.Restaurante.Pedidos.persistence.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Embeddable
public class OrderDishKey implements Serializable {
    private static final long serialVersionUID = -6092761566257937169L;

    @Column(name = "ID_PLATO")
    @Comment("FK - Identificador de la tabla menu_platos")
    private Integer dishId;

    @Column(name = "ID_ORDEN")
    @Comment("FK - Identificador de la tabla pedidos")
    private Integer orderId;
}
