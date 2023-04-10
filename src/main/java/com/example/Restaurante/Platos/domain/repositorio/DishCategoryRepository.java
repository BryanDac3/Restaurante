package com.example.Restaurante.Platos.domain.repositorio;

import com.example.Restaurante.Platos.persistence.entity.MenuCategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishCategoryRepository extends JpaRepository<MenuCategoryEntity, Integer> {
}
