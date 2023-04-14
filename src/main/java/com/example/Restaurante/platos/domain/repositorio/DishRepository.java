package com.example.Restaurante.platos.domain.repositorio;

import com.example.Restaurante.platos.persistence.entity.MenuDishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface DishRepository extends JpaRepository<MenuDishEntity, Integer> {
    Optional<MenuDishEntity> findMenuDishEntityByNameAndRestaurantId(String name, Integer restaurantId);
    Optional<MenuDishEntity> findMenuDishEntityById(Integer id);
    @Query(value = "select ID_RES from usuarios users where users.ID_USU = :ownerId",
            nativeQuery = true)
    Optional<Integer> findRestaurantIdByOwnerId(@Param("ownerId") Integer ownerId);

    @Query(value = "select ID_RES from restaurantes restaurant where restaurant.ID_RES = :id",
            nativeQuery = true)
    Optional<Integer> findRestaurantById(@Param("id") Integer id);

    @Query(value = "SELECT * FROM menu_platos where ID_RES = :restaurantId and ACTIVO = 1 GROUP BY CAT order by CAT, NOM_PLA",
            nativeQuery = true)
    Page<MenuDishEntity> findMenuDishEntityActiveByRestaurantIdWithPagination(
            @Param("restaurantId") Integer restaurantId,
            Pageable pageable);

    @Query(value = "SELECT * FROM menu_platos where ID_RES = :restaurantId GROUP BY CAT order by CAT, NOM_PLA",
            nativeQuery = true)
    Page<MenuDishEntity> findMenuDishEntityByRestaurantIdWithPagination(
            @Param("restaurantId") Integer restaurantId,
            Pageable pageable);

    @Modifying
    @Transactional
    @Query(value = "update menu_platos set PRE = :price, DES = :description where ID_PLA = :dishId",
            nativeQuery = true)
    int updateMenuDish(
            @Param("dishId") Integer dishId,
            @Param("description") String description,
            @Param("price") Integer price);

    @Modifying
    @Transactional
    @Query(value = "update menu_platos set ACTIVO = :active where ID_PLA = :dishId",
            nativeQuery = true)
    int activeMenuDish(
            @Param("dishId") Integer dishId,
            @Param("active") Integer active);
}
