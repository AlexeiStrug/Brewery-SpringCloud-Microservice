package com.example.beer.order.service.config.sm.actions;

import com.example.beer.order.service.config.JmsConfig;
import com.example.beer.order.service.domain.BeerOrder;
import com.example.beer.order.service.domain.enums.BeerOrderEventEnum;
import com.example.beer.order.service.domain.enums.BeerOrderStatusEnum;
import com.example.beer.order.service.repositories.BeerOrderRepository;
import com.example.beer.order.service.services.BeerOrderManagerImpl;
import com.example.beer.order.service.web.mappers.BeerOrderMapper;
import com.example.brewery.model.events.DeallocateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeallocateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> stateContext) {
        String beerOrderId = (String) stateContext.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        Optional<BeerOrder> beerOrderOptional = beerOrderRepository.findById(UUID.fromString(beerOrderId));

        beerOrderOptional.ifPresentOrElse(beerOrder -> {
            jmsTemplate.convertAndSend(JmsConfig.DEALLOCATE_ORDER_QUEUE,
                    DeallocateOrderRequest.builder()
                            .beerOrderDto(beerOrderMapper.beerOrderToDto(beerOrder))
                            .build());
        }, () -> log.error("Order Not Found. Id: " + beerOrderId));

        log.debug("Sent Deallocation request to queue for order id " + beerOrderId);
    }
}
