package com.example.Restaurante.Pedidos.persistence.entity;

import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
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
@Table(name = "pedidos")
public class OrderEntity implements Serializable {
    private static final long serialVersionUID = 7269878893505885865L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PED")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "CANTIDAD")
    @Comment("La cantidad de platos solicitada por el cliente")
    private Integer amount;

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
    @JoinColumn(name = "ID_PLA")
    @Comment("FK - Tabla MENU_PLATOS")
    private MenuDishEntity dish;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "EST")
    @Comment("FK - Tabla ESTADO_PEDIDOS")
    private StateOrderEntity state;
}
