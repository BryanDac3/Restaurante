package com.example.Restaurante.Platos.persistence.mapper;

import com.example.Restaurante.Platos.domain.dto.CategoryInfoDTO;
import com.example.Restaurante.Platos.domain.dto.CreateDishDTO;
import com.example.Restaurante.Platos.domain.dto.ListDishesDTO;
import com.example.Restaurante.Platos.domain.dto.UpdateDishDTO;
import com.example.Restaurante.Platos.persistence.entity.MenuCategoryEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DishEntityToDishDTO {

    DishEntityToDishDTO INSTANCE = Mappers.getMapper(DishEntityToDishDTO.class);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "restaurant", ignore = true),
            @Mapping(target = "category.id", source = "categoryId")
    })
    MenuDishEntity createDishDTOToMenuDishEntity(CreateDishDTO dish);

    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "restaurant", ignore = true),
            @Mapping(target = "category", ignore = true)
    })
    MenuDishEntity updateDishDTOToMenuDishEntity(UpdateDishDTO dish);

    @Mappings({
            @Mapping(target = "category.id", source = "category.id"),
            @Mapping(target = "category.name", source = "category.name"),
            @Mapping(target = "category.value", source = "category.value")
    })
    List<ListDishesDTO> listDishEntityToListDishesDTO(List<MenuDishEntity> listDish);

    @Mappings({
            @Mapping(target = "name", ignore = true),
            @Mapping(target = "active", ignore = true),
            @Mapping(target = "restaurant", ignore = true),
            @Mapping(target = "category", ignore = true)
    })
    List<CategoryInfoDTO> listMenuCategoryEntityToListCategoryInfoDTO(List<MenuCategoryEntity> category);
}
