package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Page<OrderEntity> findOrderEntityByStateIdAndRestaurantIdOrderByState(
            Integer stateId,
            Integer restaurantId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByStateIdAndChefIdOrderByState(
            Integer stateId,
            Integer chefId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByRestaurantIdOrderByState(
            Integer restaurantId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByStateIdAndClientIdOrderByState(
            Integer stateId,
            Integer clientId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByClientIdOrderByState(
            Integer clientId,
            Pageable pageable);

    Optional<OrderEntity> findOrderEntityById(Integer id);

    @Query(value = "select ID_RES from usuarios u where u.ID_USU = :employeeId",
            nativeQuery = true)
    Optional<Integer> findRestaurantIdByEmployeeId(@Param("employeeId") Integer employeeId);

    @Modifying
    @Transactional
    @Query(value = "update pedidos set ID_CHEF = :employeeId, EST = :orderStateId where ID_PED = :orderId",
            nativeQuery = true)
    int updatePreparingOrder(
            @Param("employeeId") Integer employeeId,
            @Param("orderId") Integer orderId,
            @Param("orderStateId") Integer orderStateId);

    @Modifying
    @Transactional
    @Query(value = "update pedidos set ID_CHEF = :employeeId, EST = :orderStateId, PIN = :pin where ID_PED = :orderId",
            nativeQuery = true)
    int updateFinishOrder(
            @Param("employeeId") Integer employeeId,
            @Param("orderId") Integer orderId,
            @Param("orderStateId") Integer orderStateId,
            @Param("pin") String pin);

    @Modifying
    @Transactional
    @Query(value = "update pedidos set EST = :orderStateId where ID_PED = :orderId",
            nativeQuery = true)
    int updateDeliveryOrder(
            @Param("orderId") Integer orderId,
            @Param("orderStateId") Integer orderStateId);

    @Modifying
    @Transactional
    @Query(value = "update pedidos set EST = :orderStateId where ID_PED = :orderId",
            nativeQuery = true)
    int updateCancelOrder(
            @Param("orderId") Integer orderId,
            @Param("orderStateId") Integer orderStateId);

    @Query(value = "select users.CEL_USU from pedidos ped " +
            "join usuarios users on users.ID_USU = ped.ID_CLI where ped.ID_PED = :orderId",
            nativeQuery = true)
    Optional<String> findClientNumberByOderId(
            @Param("orderId") Integer orderId
    );
    @Query(value = "select ped.pin from pedidos ped where ped.pin = :pin and ped.est = :orderStateId",
            nativeQuery = true)
    Optional<String> findPinByOderId(
            @Param("pin") String pin,
            @Param("orderStateId") Integer orderStateId
    );

}
