package com.example.Restaurante.Pedidos.domain.repositorio;

import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishORepository extends JpaRepository<MenuDishEntity, Integer> {

    Optional<MenuDishEntity> findMenuDishEntityById(Integer id);
}
