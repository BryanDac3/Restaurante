package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    Page<OrderEntity> findOrderEntityByStateId(
            Integer stateId,
            Pageable pageable);
    Page<OrderEntity> findOrderEntityByStateIdAndChefId(
            Integer stateId,
            Integer chefId,
            Pageable pageable);
}
