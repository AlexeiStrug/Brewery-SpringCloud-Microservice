package com.example.beer.order.service.services.textcomponents;

import com.example.beer.order.service.config.JmsConfig;
import com.example.brewery.model.events.ValidateOrderRequest;
import com.example.brewery.model.events.ValidateOrderResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerOrderValidationListener {

    private final JmsTemplate jmsTemplate;

    @JmsListener(destination = JmsConfig.VALIDATE_ORDER_QUEUE)
    public void list(Message message) {
        boolean isValid = true;

        ValidateOrderRequest request = (ValidateOrderRequest) message.getPayload();

        if (request.getBeerOrder().getCustomerRef() != null && request.getBeerOrder().getCustomerRef().equals("fail-validation"))
            isValid = false;

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_RESPONSE_QUEUE, ValidateOrderResult.builder()
                .isValid(isValid)
                .orderId(request.getBeerOrder().getId())
                .build());
    }
}
