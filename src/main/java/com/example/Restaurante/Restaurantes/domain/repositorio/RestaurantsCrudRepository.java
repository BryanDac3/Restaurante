package com.example.Restaurante.Restaurantes.domain.repositorio;

import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface RestaurantsCrudRepository extends JpaRepository<RestaurantEntity, Integer> {

    Optional<RestaurantEntity> findRestaurantEntityByNit(String nit);
    Optional<RestaurantEntity> findRestaurantEntityById(Integer id);

    @Query(value = "select * from restaurantes rest where rest.ID_RES = " +
            "( select ID_RES from usuarios users where users.ID_USU = :ownerId)",
            nativeQuery = true)
    Optional<RestaurantEntity> findRestaurantEntityByUserId(@Param("ownerId") Integer ownerId);

    @Modifying
    @Transactional
    @Query(value = "update usuarios set ID_RES = :idRestaurant where ID_USU = :idUser",
            nativeQuery = true)
    int updateRestaurantUser(
            @Param("idRestaurant") Integer idRestaurant,
            @Param("idUser") Integer idUser);

    @Query(value = "SELECT * FROM restaurantes ORDER BY NOM_RES",
        nativeQuery = true)
    Page<RestaurantEntity> findRestaurantEntityWithPagination(Pageable pageable);
}
