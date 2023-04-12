package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Page<OrderEntity> findOrderEntityByStateIdAndRestaurantId(
            Integer stateId,
            Integer restaurantId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByStateIdAndChefId(
            Integer stateId,
            Integer chefId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByRestaurantId(
            Integer restaurantId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByStateIdAndClientId(
            Integer stateId,
            Integer clientId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByClientId(
            Integer clientId,
            Pageable pageable);

    @Query(value = "select ID_RES from usuarios u where u.ID_USU = :employeeId",
            nativeQuery = true)
    Optional<Integer> findRestaurantIdByEmployeeId(@Param("employeeId") Integer employeeId);
}
