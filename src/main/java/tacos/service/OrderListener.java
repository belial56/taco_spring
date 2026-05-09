package tacos.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tacos.DTO.OrderDTO;
import tacos.utils.converter.TacoOrderMapper;

@Service
@Slf4j
public class OrderListener {

    private final OrderService orderService;
    private final TacoOrderMapper mapper;

    public OrderListener(OrderService orderService, TacoOrderMapper mapper){
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(OrderDTO order){
        log.info("Order received" + order.toString());
        orderService.save(mapper.toEntity(order));
    }
}
