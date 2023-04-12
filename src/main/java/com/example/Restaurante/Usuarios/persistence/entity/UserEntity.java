package com.example.Restaurante.Usuarios.persistence.entity;


import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.sun.istack.NotNull;
import lombok.*;
import org.hibernate.annotations.Comment;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;

@Getter
@Setter
@Data
@Entity
@Table(name = "usuarios")
public class UserEntity implements Serializable{
    private static final long serialVersionUID = -2689403226662903023L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_USU")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "NOM_USU")
    @Comment("Nombre del usuario registrado")
    private String names;

    @NotNull
    @Column(name = "APELLIDOS")
    @Comment("Apellidos del usuario registrado")
    private String lastNames;

    @NotNull
    @Column(name = "CEL_USU")
    @Comment("Numero de contacto del usuario")
    private String cellNumber;

    @NotNull
    @Column(name = "DOC_ID")
    @Comment("Documento de identificacion del usuario")
    private String idNumber;

    @NotNull
    @Column(name = "EMAIL")
    @Comment("Correo electronico del usuario")
    private String email;

    @NotNull
    @Column(name = "PASS", length = 500)
    @Comment("Contrasena del usuario")
    private String password;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "ID_ROL")
    @Comment("FK - Tabla ROLES")
    private RolEntity rol;

    @ManyToOne
    @JoinColumn(name = "ID_RES")
    @Comment("FK - Tabla RESTAURANTES")
    private RestaurantEntity restaurant;

    public UserEntity(Integer id) {
        this.id = id;
    }
}
