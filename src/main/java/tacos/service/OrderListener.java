package tacos.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tacos.model.TacoOrder;

@Service
public class OrderListener {

    @KafkaListener(topics = "tacocloud.orders.topic")
    public void handle(TacoOrder order){
        System.out.println("Order received" + order.toString());
    }
}
