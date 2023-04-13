package com.example.Restaurante.Pedidos.domain.service;

import com.example.Restaurante.Pedidos.domain.repositorio.DishORepository;
import com.example.Restaurante.Pedidos.domain.repositorio.OrderDishRepository;
import com.example.Restaurante.Pedidos.domain.repositorio.OrderRepository;
import com.example.Restaurante.Pedidos.domain.repositorio.OrderStateRepository;
import com.example.Restaurante.Pedidos.persistence.entity.OrderDishEntity;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.Pedidos.persistence.entity.OrderStateEntity;
import com.example.Restaurante.Platos.persistence.entity.MenuDishEntity;
import com.example.Restaurante.Restaurantes.persistence.entity.RestaurantEntity;
import com.example.Restaurante.Usuarios.persistence.entity.UserEntity;
import com.example.Restaurante.config.Twilio.TwilioConfiguration;
import com.example.Restaurante.config.error.RestException;
import com.example.Restaurante.emun.OrderStateE;
import com.example.Restaurante.emun.RestExceptionE;
import com.example.Restaurante.emun.RolE;
import com.example.Restaurante.utils.Utils;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class OrdersServiceImpl implements OrdersService{

    private final TwilioConfiguration twilioConfiguration;

    private static final Logger LOG = LoggerFactory.getLogger(OrdersServiceImpl.class);

    private String MESSAGE_SMS = "Message.sms";
    public static final int NUMBER_PIN_RANGE_MAX = 8;
    public static final int NUMBER_PIN_RANGE_MIN = 0;

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
    private MessageSource messageSource;

    @Autowired
    public OrdersServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    @Transactional
    public ResponseEntity<Void> makeOrder(
            List<OrderDishEntity> orders, Integer clientId, String rolValue) throws RestException {
        utils.validateUserRol(rolValue, RolE.CLIENT_VALUE);
        Integer restaurantId = null;
        List<Integer> dishesId = new ArrayList<>();
        Integer totalCount = 0;

        for(OrderDishEntity order: orders){
            MenuDishEntity dishDB = validateOrderInformation(order);
            if(restaurantId == null){
                restaurantId = dishDB.getRestaurant().getId();
            }
            validateSameRestaurantDishes(restaurantId, dishDB);
            order.setDishName(dishDB.getName());
            dishesId.add(dishDB.getId());
            totalCount = getOrderTotalCount(totalCount, order.getAmount(), dishDB.getPrice());
        }
        validateDuplicateDish(dishesId);

        OrderEntity newOrder = createOrder(restaurantId, totalCount, clientId);
        createOrdersDish(orders, newOrder);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Integer getOrderTotalCount(Integer totalCount, Integer amountDish, Integer priceDish){
        Integer totalDishPrice = amountDish * priceDish;
        return totalCount += totalDishPrice;
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
        if(duplicateDishId != null && !duplicateDishId.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_ORDER_DUPLICATE_DISH);
        }
    }

    private OrderEntity createOrder(Integer restaurantId, Integer totalPrice, Integer clientId) {
        OrderEntity orderEntity = new OrderEntity();
        UserEntity clientEntity = new UserEntity();
        clientEntity.setId(clientId);
        RestaurantEntity restaurant = new RestaurantEntity();
        restaurant.setId(restaurantId);

        orderEntity.setClient(clientEntity);
        orderEntity.setRestaurant(restaurant);
        orderEntity.setTotalPrice(totalPrice);
        orderEntity.setState(orderStateRepository.findOrderStateEntityByValue(
                OrderStateE.PENDING_ORDER.getValue()).get()
        );
        OrderEntity newOrder = orderRepository.save(orderEntity);
        return newOrder;
    }

    private void createOrdersDish(List<OrderDishEntity> orders, OrderEntity orderEntity) {
        for(OrderDishEntity order: orders){
            order.getId().setOrderId(orderEntity.getId());
            order.setOrderEntity(orderEntity);
            orderDishRepository.save(order);
        }
    }

    @Override
    public List<OrderEntity> listOrderEmployee(
            String orderStateValue,
            Integer employeeId,
            String rolValue,
            Pageable pageable
    ) throws RestException {
        utils.validateUserRol(rolValue, RolE.EMPLOYEE_VALUE);
        Integer restaurantId = orderRepository.findRestaurantIdByEmployeeId(employeeId).get();
        if(orderStateValue != null){
            return listOrderWithStateFilter(employeeId, restaurantId, orderStateValue, pageable);
        }
        else{
            return listOrderWithOutStateFilter(restaurantId, pageable);
        }
    }

    private List<OrderEntity> listOrderWithStateFilter(Integer employeeId, Integer restaurantId, String orderStateValue, Pageable pageable) throws RestException {
        Page<OrderEntity> ordersPaged;
        Optional<OrderStateEntity> orderStateO = orderStateRepository.findOrderStateEntityByValue(orderStateValue);
        if(orderStateO.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_ORDER_STATE_NOT_EXIST);
        }
        OrderStateEntity orderState = orderStateO.get();

        if(orderState.getValue().equals(OrderStateE.PENDING_ORDER.getValue())
                || orderState.getValue().equals(OrderStateE.CANCEL_ORDER.getValue())){
            ordersPaged = orderRepository.findOrderEntityByStateIdAndRestaurantIdOrderByState(
                    orderState.getId(), restaurantId, pageable);
        }
        else{
            ordersPaged = orderRepository.findOrderEntityByStateIdAndChefIdOrderByState(orderState.getId(), employeeId, pageable);
        }
        return ordersPaged.getContent();
    }

    private List<OrderEntity> listOrderWithOutStateFilter(Integer restaurantId, Pageable pageable) throws RestException {
        Page<OrderEntity> ordersPaged = orderRepository.findOrderEntityByRestaurantIdOrderByState(restaurantId, pageable);
        return ordersPaged.getContent();
    }

    @Override
    public ResponseEntity<Void> prepareOrderEmployee(Integer orderId, Integer employeeId, String rolValue) throws RestException {
        utils.validateUserRol(rolValue, RolE.EMPLOYEE_VALUE);

        OrderEntity orderDb = validateEmployeeOrderSameRestaurant(orderId, employeeId);
        validateOrderState(orderDb, OrderStateE.PENDING_ORDER, RestExceptionE.ERROR_ORDER_NOT_PENDING);

        Optional<OrderStateEntity> orderState = orderStateRepository.findOrderStateEntityByValue(OrderStateE.PREPARATION_ORDER.getValue());
        orderRepository.updatePreparingOrder(employeeId, orderId, orderState.get().getId());
        return ResponseEntity.ok().build();
    }

    private OrderEntity validateEmployeeOrderSameRestaurant(Integer orderId, Integer employeeId) throws RestException {
        Optional<OrderEntity> orderDb = orderRepository.findOrderEntityById(orderId);
        if(orderDb.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_ORDER_NOT_EXIST);
        }
        Integer restaurantEmployeeId = orderRepository.findRestaurantIdByEmployeeId(employeeId).get();

        if(!restaurantEmployeeId.equals(orderDb.get().getRestaurant().getId())){
            throw new RestException(RestExceptionE.ERROR_ORDER_EMPLOYEE_NOT_SAME_RESTAURANT);
        }
        return orderDb.get();
    }

    private void validateOrderState(
            OrderEntity order, OrderStateE orderStateE, RestExceptionE restExceptionE) throws RestException{
        if(!order.getState().getValue().equals(orderStateE.getValue())){
            throw new RestException(restExceptionE);
        }
    }

    @Override
    public ResponseEntity<Void> finishOrderEmployee(Integer orderId, Integer employeeId, String rolValue) throws RestException {
        utils.validateUserRol(rolValue, RolE.EMPLOYEE_VALUE);

        OrderEntity orderDb = validateEmployeeOrderSameRestaurant(orderId, employeeId);
        validateOrderState(orderDb, OrderStateE.PREPARATION_ORDER, RestExceptionE.ERROR_ORDER_NOT_PREPARING);
        Optional<OrderStateEntity> orderState = orderStateRepository.findOrderStateEntityByValue(OrderStateE.FINISH_ORDER.getValue());

        String pin = createPINOrder();
        orderRepository.updateFinishOrder(employeeId, orderId, orderState.get().getId(), pin);

        String clientPhoneNumber = orderRepository.findClientNumberByOderId(orderId).get();
        sendClientSMS(clientPhoneNumber, pin);
        return null;
    }

    private String sendClientSMS(String phoneNumber, String pin) throws RestException {
        try{
            PhoneNumber to = new PhoneNumber(phoneNumber);
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = messageSource.getMessage(MESSAGE_SMS, null, LocaleContextHolder.getLocale()) + pin;
            MessageCreator messageCreator = Message.creator(to, from, message);
            messageCreator.create();
            LOG.info("Se envia al numero {}", phoneNumber);
        }catch (Exception e){
            LOG.error("No fue posible enviar el SMS error: {}", e);
            throw new RestException(RestExceptionE.ERROR_SEND_SMS);
        };
        return pin;
    }

    private String createPINOrder(){
        boolean createdPin = true;
        while (createdPin){
            String pin = "";
            for(int i = NUMBER_PIN_RANGE_MIN; i < NUMBER_PIN_RANGE_MAX; i++){
                String numberPin = String.valueOf((int)(Math.random() * 9 + 1));
                pin += numberPin;
            }
            Optional<OrderStateEntity> orderState = orderStateRepository.findOrderStateEntityByValue(OrderStateE.FINISH_ORDER.getValue());
            Optional<String> pinDB = orderRepository.findPinByOderId(pin, orderState.get().getId());
            if(pinDB.isEmpty()){
                createdPin = false;
                return pin;
            }
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> deliverOrderEmployee(Integer orderId, String pin, Integer employeeId, String rolValue) throws RestException {
        utils.validateUserRol(rolValue, RolE.EMPLOYEE_VALUE);

        OrderEntity orderDb = validateEmployeeOrderSameRestaurant(orderId, employeeId);
        validateOrderState(orderDb, OrderStateE.FINISH_ORDER, RestExceptionE.ERROR_ORDER_NOT_FINISH);
        Optional<OrderStateEntity> orderState = orderStateRepository.findOrderStateEntityByValue(OrderStateE.DELIVERY_ORDER.getValue());

        if(!orderDb.getPIN().equals(pin)){
            throw new RestException(RestExceptionE.ERROR_ORDER_PIN_NOT_VALID);
        }

        orderRepository.updateDeliveryOrder(orderId, orderState.get().getId());

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> cancelOrderClient(Integer orderId, Integer clientId, String rolValue) throws RestException {
        utils.validateUserRol(rolValue, RolE.CLIENT_VALUE);
        Optional<OrderEntity> orderDb = orderRepository.findOrderEntityById(orderId);
        if(orderDb.isEmpty()){
            throw new RestException(RestExceptionE.ERROR_ORDER_NOT_EXIST);
        }
        validateOrderState(orderDb.get(), OrderStateE.PENDING_ORDER, RestExceptionE.ERROR_ORDER_CANNOT_CANCEL);
        Optional<OrderStateEntity> orderState = orderStateRepository.findOrderStateEntityByValue(OrderStateE.CANCEL_ORDER.getValue());
        orderRepository.updateCancelOrder(orderId, orderState.get().getId());
        return null;
    }

    @Override
    public List<OrderEntity> listOrderClient(String orderStateValue, Integer clientId, String rolValue, Pageable pageable) throws RestException {
        Page<OrderEntity> ordersPaged;
        utils.validateUserRol(rolValue, RolE.CLIENT_VALUE);

        if(orderStateValue != null){
            Optional<OrderStateEntity> orderStateO = orderStateRepository.findOrderStateEntityByValue(orderStateValue);
            if(orderStateO.isEmpty()){
                throw new RestException(RestExceptionE.ERROR_ORDER_STATE_NOT_EXIST);
            }
            OrderStateEntity orderState = orderStateO.get();
            ordersPaged = orderRepository.findOrderEntityByStateIdAndClientIdOrderByState(orderState.getId(), clientId, pageable);
        }
        else{
            ordersPaged = orderRepository.findOrderEntityByClientIdOrderByState(clientId, pageable);
        }
        return ordersPaged.getContent();
    }
}
