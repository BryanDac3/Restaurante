package com.example.Restaurante.pedidos.domain.repositorio;

import com.example.Restaurante.pedidos.persistence.entity.OrderStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStateRepository  extends JpaRepository<OrderStateEntity, Integer> {

    Optional<OrderStateEntity> findOrderStateEntityByValue(String value);
}
