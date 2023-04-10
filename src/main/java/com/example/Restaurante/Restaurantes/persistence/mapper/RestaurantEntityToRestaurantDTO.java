package com.example.Restaurante.Restaurantes.persistence.mapper;

import com.example.Restaurante.Restaurantes.domain.dto.CreateRestaurantDTO;
import com.example.Restaurante.Restaurantes.domain.dto.ListRestaurantsDTO;
import com.example.Restaurante.Restaurantes.domain.dto.RestaurantInfoDTO;
import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantEntityToRestaurantDTO {
    RestaurantEntityToRestaurantDTO INSTANCE = Mappers.getMapper(RestaurantEntityToRestaurantDTO.class);

    @Mappings({
            @Mapping(target = "restaurantNIT", source = "nit")
    })
    RestaurantInfoDTO restaurantEntityToRestaurantInfoDTO(RestaurantEntity restaurant);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "nit", source = "restaurantNIT")

    })
    RestaurantEntity createRestaurantDTOToRestaurantEntity(CreateRestaurantDTO restaurantDTO);

    List<ListRestaurantsDTO> restaurantEntityToListRestaurantsDTO(List<RestaurantEntity> restaurant);
}
