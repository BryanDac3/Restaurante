package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Pedidos.persistence.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishRepository  extends JpaRepository<OrderDishEntity, Integer> {
}
