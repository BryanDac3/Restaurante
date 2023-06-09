package com.example.Restaurante.pedidos.persistence.entity;

import com.example.Restaurante.restaurantes.persistence.entity.RestaurantEntity;
import com.example.Restaurante.usuarios.persistence.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "pedidos")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 7269878893505885865L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PED")
    @Comment("Identificador de la tabla")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "ID_CHEF")
    @Comment("FK - Tabla USUARIOS")
    private UserEntity chef;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_CLI")
    @Comment("FK - Tabla USUARIOS")
    private UserEntity client;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EST")
    @Comment("FK - Tabla ESTADO_PEDIDOS")
    private OrderStateEntity state;

    @ManyToOne
    @JoinColumn(name = "ID_RES")
    @Comment("FK - Tabla RESTAURANTES")
    private RestaurantEntity restaurant;

    @Column(name = "PRECIO_TOTAL")
    @Comment("Precio total de la cuenta")
    private Integer totalPrice;

    @Column(name = "PIN")
    @Comment("PIN de validacion del pedido")
    private String PIN;

    @OneToMany(mappedBy = "orderEntity")
    private List<OrderDishEntity> orderDish;
}
