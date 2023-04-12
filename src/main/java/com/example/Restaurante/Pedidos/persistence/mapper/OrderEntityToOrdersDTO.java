package com.example.Restaurante.Pedidos.persistence.mapper;
import com.example.Restaurante.Pedidos.domain.dto.CreateOrderDTO;
import com.example.Restaurante.Pedidos.persistence.entity.OrderDishEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderEntityToOrdersDTO {

    OrderEntityToOrdersDTO INSTANCE = Mappers.getMapper(OrderEntityToOrdersDTO.class);
    @Mappings({
            @Mapping(target = "orderEntity", ignore = true),
            @Mapping(target = "dishEntity", ignore = true),
            @Mapping(target = "id.dishId", source = "dishId")
    })
    OrderDishEntity createOrderDTOToOrderDishEntity(CreateOrderDTO orderDTO);
    List<OrderDishEntity> listCreateOrderDTOToListOrderDishEntity(List<CreateOrderDTO> orderDTO);
}
