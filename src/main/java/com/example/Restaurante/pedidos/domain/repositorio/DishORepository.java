package com.example.Restaurante.pedidos.domain.repositorio;

import com.example.Restaurante.platos.persistence.entity.MenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DishORepository extends JpaRepository<MenuDishEntity, Integer> {

    Optional<MenuDishEntity> findMenuDishEntityById(Integer id);
}
