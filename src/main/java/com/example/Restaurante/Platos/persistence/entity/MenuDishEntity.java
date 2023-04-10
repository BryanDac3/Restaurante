package com.example.Restaurante.Platos.persistence.entity;

import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "menu_platos")
public class MenuDishEntity implements Serializable {
    private static final long serialVersionUID = -7425293221035130708L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PLA")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "NOM_PLA")
    @Comment("Nombre del plato")
    private String name;

    @NotNull
    @Column(name = "PRE")
    @Comment("Precio del plato")
    private Integer price;

    @NotNull
    @Column(name = "ACTIVO")
    @Comment("Estado del plato 0 - Inactivo, 1 - Activo")
    private Integer active;

    @Column(name = "DES")
    @Comment("Descripcion del plato")
    private String description;

    @Column(name = "URL_PLAT")
    @Comment("Campo con la url de la imagen del plato")
    private String urlImgDish;

    @ManyToOne
    @JoinColumn(name = "ID_RES")
    @Comment("FK - Tabla 'RESTAURANTES'")
    private RestaurantEntity restaurant;

    @ManyToOne
    @JoinColumn(name = "CAT")
    @Comment("FK - Tabla 'MENU_CATEGORIAS'")
    private MenuCategoryEntity category;

}
