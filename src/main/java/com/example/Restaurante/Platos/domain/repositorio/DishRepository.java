package com.example.Restaurante.Platos.domain.repositorio;

import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<MenuDishEntity, Integer> {
}
