package com.example.Restaurante.Pedidos.web;

import com.example.Restaurante.Pedidos.domain.dto.CreateOrderDTO;
import com.example.Restaurante.Pedidos.domain.dto.ListOrdersDTO;
import com.example.Restaurante.Pedidos.domain.service.OrdersService;
import com.example.Restaurante.Pedidos.persistence.entity.OrderDishEntity;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Pedidos.persistence.mapper.OrderEntityToOrdersDTO;
import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.config.security.UserDetailsImpl;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Validated
public class OrdersController implements OrdersAPI {
    private static final Logger LOG = LoggerFactory.getLogger(RestauranteApplication.class);

    @Autowired
    private OrdersService ordersService;

    @Autowired
    private Utils utils;

    @Override
    public ResponseEntity<Void> makeOrder(List<CreateOrderDTO> ordersDTO) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        List<OrderDishEntity> orders =
                OrderEntityToOrdersDTO.INSTANCE.listCreateOrderDTOToListOrderDishEntity(ordersDTO);
        return ordersService.makeOrder(
                orders,
                userDetails.getId(),
                userDetails.getRol().getValue()
        );
    }

    @Override
    public ResponseEntity<List<ListOrdersDTO>> listOrderEmployee(String orderStateValue, Pageable pageable) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        List<OrderEntity> orders =  ordersService.listOrderEmployee(
                orderStateValue,
                userDetails.getId(),
                userDetails.getRol().getValue(),
                pageable);
        List<ListOrdersDTO> ordersDTOS = OrderEntityToOrdersDTO.INSTANCE.listOderEntityToListOrdersDTO(orders);
        return ResponseEntity.ok().body(ordersDTOS);
    }

    @Override
    public ResponseEntity<Void> prepareOrderEmployee(Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        return ordersService.prepareOrderEmployee(
                orderId,
                userDetails.getId(),
                userDetails.getRol().getValue()
        );
    }

    @Override
    public ResponseEntity<Void> finishOrderEmployee(Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.finishOrderEmployee(
                orderId,
                userDetails.getId(),
                userDetails.getRol().getValue()
        );
        return null;
    }

    @Override
    public ResponseEntity<Void> deliverOrderEmployee(String pin, Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        return ordersService.deliverOrderEmployee(
                orderId,
                pin,
                userDetails.getId(),
                userDetails.getRol().getValue()
        );
    }

    @Override
    public ResponseEntity<Void> cancelOrderClient(Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        return ordersService.cancelOrderClient(
                orderId,
                userDetails.getId(),
                userDetails.getRol().getValue()
        );
    }

    @Override
    public ResponseEntity<ListOrdersDTO> listOrderClient(String orderStateValue, Pageable pageable) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.listOrderClient(
                orderStateValue,
                userDetails.getId(),
                userDetails.getRol().getValue(),
                pageable
        );
        return null;
    }


}
