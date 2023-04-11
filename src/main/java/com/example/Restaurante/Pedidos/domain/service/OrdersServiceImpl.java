package com.example.Restaurante.Pedidos.domain.service;

import com.example.Restaurante.Pedidos.domain.dto.SmsRequest;
import com.example.Restaurante.Pedidos.persistence.entity.OrderEntity;
import com.example.Restaurante.config.Twilio.TwilioConfiguration;
import com.example.Restaurante.config.error.RestException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService{

    private final TwilioConfiguration twilioConfiguration;

    private static final Logger LOG = LoggerFactory.getLogger(OrdersServiceImpl.class);

    @Autowired
    public OrdersServiceImpl(TwilioConfiguration twilioConfiguration) {
        this.twilioConfiguration = twilioConfiguration;
    }

    @Override
    public ResponseEntity<Void> makeOrder(List<OrderEntity> orderEntities, Integer clientId, String rolValue) throws RestException {
        return null;
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
