package com.example.Restaurante.Restaurantes.persistence.entity;

import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
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
@Table(name = "restaurantes")
public class RestaurantEntity implements Serializable {
    private static final long serialVersionUID = 2526279018580227361L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_RES")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "NOM_RES")
    @Comment("Nombre del restaurante")
    private String name;

    @NotNull
    @Column(name = "NIT")
    @Comment("Campo con la informacion del NIT del restaurante")
    private String nit;

    @NotNull
    @Column(name = "TEL")
    @Comment("Numero de contacto del restaurante")
    private String contactNumber;

    @Column(name = "DIR")
    @Comment("Direccion o ubicacion del restaurante")
    private String direction;

    @Column(name = "DESC_RES")
    @Comment("Descripcion del restaurante")
    private String description;

    @Column(name = "URL_LOGO")
    @Comment("Campo con la url del logo del restaurante")
    private String urlLogo;

    public RestaurantEntity() {

    }
}
