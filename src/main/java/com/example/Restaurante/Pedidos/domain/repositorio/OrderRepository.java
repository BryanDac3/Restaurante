package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
}
