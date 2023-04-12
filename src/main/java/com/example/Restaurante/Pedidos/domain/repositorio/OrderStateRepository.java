package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Pedidos.persistence.entity.OrderStateEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStateRepository  extends JpaRepository<OrderStateEntity, Integer> {

    Optional<OrderStateEntity> findOrderStateEntityByValue(String value);
}
