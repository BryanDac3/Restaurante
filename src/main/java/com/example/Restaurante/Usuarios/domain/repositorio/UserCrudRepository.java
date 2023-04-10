package com.example.Restaurante.Usuarios.domain.repositorio;

import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserCrudRepository extends JpaRepository<UserEntity, Integer> {

    UserEntity findUserEntityByNamesAndIdNumber(String name, String idNumber);
    UserEntity findUserEntityById(Integer id);
    Optional<UserEntity> findOneByEmail(String email);

    @Query(value = "select ID_RES from usuarios users where users.ID_USU = :userId",
            nativeQuery = true)
    Optional<Integer> findRestaurantIdByUserId(@Param("userId") Integer userId);
    @Modifying
    @Query(value = "INSERT INTO usuarios (NOM_USU, APELLIDOS, DOC_ID, EMAIL, PASS, CEL_USU, ID_ROL)" +
            "VALUES (:name, :lastName, :idNumber, :email, :password, :cell, :idRol)",
            nativeQuery = true)
    void saveUser(
            @Param("name") String name,
            @Param("lastName") String lastName,
            @Param("idNumber") String idNumber,
            @Param("email") String email,
            @Param("password") String password,
            @Param("cell") String cell,
            @Param("idRol") Integer idRol );
}
