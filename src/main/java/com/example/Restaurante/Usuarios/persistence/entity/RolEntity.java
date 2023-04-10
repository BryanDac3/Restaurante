package com.example.Restaurante.Usuarios.persistence.entity;

import com.sun.istack.NotNull;
import lombok.Builder;
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
@Table(name = "roles")
public class RolEntity implements Serializable{
    private static final long serialVersionUID = -9067375413566092180L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ROL")
    @Comment("Identificador de la tabla")
    private Integer id;

    @NotNull
    @Column(name = "NOM_ROL")
    @Comment("Nombre del rol del usuario")
    private String name;

    @NotNull
    @Column(name = "VAL")
    @Comment("Valor del rol del usuario")
    private String value;

    public RolEntity() {

    }
}
