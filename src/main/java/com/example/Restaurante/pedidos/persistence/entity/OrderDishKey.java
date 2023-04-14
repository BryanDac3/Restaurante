package com.example.Restaurante.pedidos.persistence.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@Data
@Embeddable
public class OrderDishKey implements Serializable {
    private static final long serialVersionUID = 1415720477194021283L;

    @Column(name = "ID_PLATO")
    @Comment("FK - Identificador de la tabla menu_platos")
    private Integer dishId;

    @Column(name = "ID_ORDEN")
    @Comment("FK - Identificador de la tabla pedidos")
    private Integer orderId;
}
