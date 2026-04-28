package com.kor4a.tacocloudclient.service;

import com.kor4a.tacocloudclient.model.TacoOrder;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaOrderMessagingService
        implements OrderMessagingService {

    private KafkaTemplate<String, TacoOrder> kafkaTemplate;

    public KafkaOrderMessagingService(KafkaTemplate<String,TacoOrder> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void sendOrder(TacoOrder order) {
        kafkaTemplate.send("tacocloud.orders.topic", order);
    }
}
