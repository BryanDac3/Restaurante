package com.example.Restaurante.Platos.domain.repositorio;

import com.example.Restaurante.Platos.persistence.entity.MenuCategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface DishCategoryRepository extends JpaRepository<MenuCategoryEntity, Integer> {

    Optional<MenuCategoryEntity> findMenuCategoryEntityById(Integer id);

    @Query(value = "select * from menu_categorias",
            nativeQuery = true)
    List<MenuCategoryEntity> findAllMenuCategoryEntity();
}
