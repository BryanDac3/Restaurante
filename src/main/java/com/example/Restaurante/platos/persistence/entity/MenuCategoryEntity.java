package com.example.Restaurante.platos.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@Entity
@Table(name = "menu_categorias")
public class MenuCategoryEntity implements Serializable{
    private static final long serialVersionUID = 4273783354759990357L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAT")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "NOM_CAT")
    @Comment("Nombre de la categoria")
    private String name;

    @NotNull
    @Column(name = "VAL")
    @Comment("Valor de la categoria")
    private String value;

    @OneToMany(mappedBy = "category")
    private List<MenuDishEntity> dishes;
}
