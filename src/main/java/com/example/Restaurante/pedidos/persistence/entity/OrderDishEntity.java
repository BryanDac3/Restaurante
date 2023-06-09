package com.example.Restaurante.pedidos.persistence.entity;

import com.example.Restaurante.platos.persistence.entity.MenuDishEntity;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "pedido_plato")
public class OrderDishEntity implements Serializable {
    private static final long serialVersionUID = 4182885217496136088L;

    @EmbeddedId
    private OrderDishKey id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "ID_PED")
    private OrderEntity orderEntity;

    @ManyToOne
    @MapsId("dishId")
    @JoinColumn(name = "ID_PLA")
    private MenuDishEntity dishEntity;

    @NotNull
    @Column(name = "CANTIDAD")
    @Comment("La cantidad de platos solicitada por el cliente")
    private Integer amount;

    @NotNull
    @Column(name = "NOMBRE_PLATO")
    @Comment("El nombre del plato para el que se realiza el pedido")
    private String dishName;

}
