package com.example.Restaurante.Pedidos.domain.service;

import com.example.Restaurante.Pedidos.domain.dto.SmsRequest;
import com.example.Restaurante.Pedidos.domain.repositorio.DishORepository;
import com.example.Restaurante.Pedidos.domain.repositorio.OrderDishRepository;
import com.example.Restaurante.Pedidos.domain.repositorio.OrderRepository;
import com.example.Restaurante.Pedidos.domain.repositorio.OrderStateRepository;
import com.example.Restaurante.Pedidos.persistence.entity.OrderDishEntity;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.config.Twilio.TwilioConfiguration;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.emun.OrderStateE;
import com.example.Restaurante.emun.RestExceptionE;
import com.example.Restaurante.utils.Utils;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrdersServiceImpl implements OrdersService{

    private final TwilioConfiguration twilioConfiguration;

    private static final Logger LOG = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Autowired
    private Utils utils;
    @Autowired
    private DishORepository dishRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDishRepository orderDishRepository;
    @Autowired
    private OrderStateRepository orderStateRepository;

    @Autowired
    public OrdersServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> makeOrder(
            List<OrderDishEntity> orders, Integer clientId, String rolValue) throws RestException {
        Integer restaurantId = null;
        List<Integer> dishesId = new ArrayList<>();
        Integer totalCount = 0;

        for(OrderDishEntity order: orders){
            MenuDishEntity dishDB = validateOrderInformation(order);
            if(restaurantId == null){
                restaurantId = dishDB.getRestaurant().getId();
            }
            validateSameRestaurantDishes(restaurantId, dishDB);
            dishesId.add(dishDB.getId());
            getOrderTotalCount(totalCount, order.getAmount(), dishDB.getPrice());
        }
        validateDuplicateDish(dishesId);

        OrderEntity newOrder = createOrder(totalCount, clientId);
        createOrderDish(orders, newOrder.getId());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private void getOrderTotalCount(Integer totalCount, Integer amountDish, Integer priceDish){
        Integer totalDishPrice = amountDish * priceDish;
        totalCount += totalDishPrice;
    }
    private MenuDishEntity validateOrderInformation(OrderDishEntity order) throws RestException {
        if(order.getAmount() == null || order.getAmount() < 1){
            throw new RestException(RestExceptionE.ERROR_AMOUNT_ORDER);
        }
        if(order.getId().getDishId() == null){
            throw new RestException(RestExceptionE.ERROR_DISH_ORDER);
        }
        Optional<MenuDishEntity> dishDB = dishRepository.findMenuDishEntityById(order.getId().getDishId());
        if(dishDB == null || dishDB.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_DISH_NOT_EXIST);
        }
        return dishDB.get();
    }

    private void validateSameRestaurantDishes(Integer restaurantId, MenuDishEntity dish) throws RestException {
        if(restaurantId != dish.getRestaurant().getId()){
            throw new RestException(RestExceptionE.ERROR_ORDER_DISH_NOT_SAME_RESTAURANT);
        }
    };
    private void validateDuplicateDish(List<Integer> listIds) throws RestException {
        Set<Integer> elements = new HashSet<>();
        List<Integer> duplicateDishId = listIds.stream()
                .filter(n -> !elements.add(n))
                .collect(Collectors.toList());
        if(duplicateDishId != null || !duplicateDishId.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_ORDER_DUPLICATE_DISH);
        }
    }

    private OrderEntity createOrder(Integer totalPrice, Integer clientId) {
        OrderEntity orderEntity = new OrderEntity();
        UserEntity clientEntity = new UserEntity(clientId);

        orderEntity.setClient(clientEntity);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setState(orderStateRepository.findOrderStateEntityByValue(
                OrderStateE.PENDING_ORDER.getValue()).get()
        );
        OrderEntity newOrder = orderRepository.save(orderEntity);
        return newOrder;
    }

    private void createOrderDish(List<OrderDishEntity> orders, Integer orderId) {
        for(OrderDishEntity order: orders){
            order.getId().setOrderId(orderId);
            orderDishRepository.save(order);
        }
    }

    @Override
    public ResponseEntity<Void> listOrderEmployee(String orderStateValue, Integer employeeId, String rolValue, Pageable pageable) throws RestException {
        return null;
    }

    @Override
    public ResponseEntity<Void> prepareOrderEmployee(Integer orderId, Integer employeeId, String rolValue) throws RestException {
        return null;
    }

    @Override
    public ResponseEntity<Void> finishOrderEmployee(Integer orderId, Integer employeeId, String rolValue) throws RestException {
        return null;
    }

    @Override
    public ResponseEntity<Void> deliverOrderEmployee(Integer orderId, String pin, Integer employeeId, String rolValue) throws RestException {
        return null;
    }

    @Override
    public ResponseEntity<Void> infoClientOrder(Integer clientId, String rolValue) throws RestException {
        return null;
    }

    @Override
    public ResponseEntity<Void> cancelOrderClient(Integer orderId, Integer clientId, String rolValue) throws RestException {
        return null;
    }

    @Override
    public ResponseEntity<Void> sendMessage(SmsRequest smsRequest) {
        PhoneNumber to = new PhoneNumber(smsRequest.getPhoneNumber());
        PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
        String message = smsRequest.getMessage();
        MessageCreator messageCreator = Message.creator(to, from, message);
        messageCreator.create();
        LOG.info("Se envia {}", smsRequest);
        return null;
    }
}
