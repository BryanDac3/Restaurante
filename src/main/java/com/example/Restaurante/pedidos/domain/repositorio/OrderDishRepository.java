package com.example.Restaurante.pedidos.domain.repositorio;

import com.example.Restaurante.pedidos.persistence.entity.OrderDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDishRepository  extends JpaRepository<OrderDishEntity, Integer> {
}
