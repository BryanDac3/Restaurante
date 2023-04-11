package com.example.Restaurante.Pedidos.web;

import com.example.Restaurante.Pedidos.domain.dto.CreateOrderDTO;
import com.example.Restaurante.Pedidos.domain.service.OrdersService;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.application.RestauranteApplication;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.config.security.UserDetailsImpl;
import com.example.Restaurante.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
        ordersService.makeOrder(null, userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }

    @Override
    public ResponseEntity<Void> listOrderEmployee(String orderStateValue) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.listOrderEmployee(orderStateValue, userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }

    @Override
    public ResponseEntity<Void> prepareOrderEmployee(Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.prepareOrderEmployee(orderId, userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }

    @Override
    public ResponseEntity<Void> finishOrderEmployee(Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.finishOrderEmployee(orderId, userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }

    @Override
    public ResponseEntity<Void> deliverOrderEmployee(String pin, Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.deliverOrderEmployee(orderId, pin, userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }

    @Override
    public ResponseEntity<Void> infoClientOrder() throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.infoClientOrder(userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }

    @Override
    public ResponseEntity<Void> cancelOrderClient(Integer orderId) throws RestException {
        UserDetailsImpl userDetails = utils.getUserInfo();
        ordersService.cancelOrderClient(orderId, userDetails.getId(), userDetails.getRol().getValue());
        return null;
    }


}
