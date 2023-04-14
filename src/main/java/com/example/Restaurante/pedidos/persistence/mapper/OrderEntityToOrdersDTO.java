package com.example.Restaurante.pedidos.persistence.mapper;
import com.example.Restaurante.pedidos.domain.dto.CreateOrderDTO;
import com.example.Restaurante.pedidos.domain.dto.ListOrdersDTO;
import com.example.Restaurante.pedidos.persistence.entity.OrderDishEntity;
import com.example.Restaurante.pedidos.persistence.entity.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderEntityToOrdersDTO {

    OrderEntityToOrdersDTO INSTANCE = Mappers.getMapper(OrderEntityToOrdersDTO.class);
    @Mappings({
            @Mapping(target = "orderEntity", ignore = true),
            @Mapping(target = "dishName", ignore = true),
            @Mapping(target = "id.dishId", source = "dishId"),
            @Mapping(target = "dishEntity.id", source = "dishId")
    })
    OrderDishEntity createOrderDTOToOrderDishEntity(CreateOrderDTO orderDTO);
    List<OrderDishEntity> listCreateOrderDTOToListOrderDishEntity(List<CreateOrderDTO> orderDTO);

    @Mappings({
            @Mapping(target = "idOrder", source = "id"),
            @Mapping(target = "stateInfo", source = "state"),
            @Mapping(target = "infoDish", source = "orderDish")
    })
    ListOrdersDTO orderEntityToListOrdersDTO(OrderEntity orderEntity);
    List<ListOrdersDTO> listOderEntityToListOrdersDTO(List<OrderEntity> orderEntity);
}
