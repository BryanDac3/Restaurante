package com.example.Restaurante.pedidos.persistence.entity;

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
@Table(name = "estado_pedidos")
public class OrderStateEntity implements Serializable {
    private static final long serialVersionUID = -279795581381232415L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EST")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "NOM_EST")
    @Comment("Nombre del estado del pedido")
    private String name;

    @NotNull
    @Column(name = "VAL")
    @Comment("valor del estado del pedido")
    private String value;
}
