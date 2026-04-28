package com.kor4a.tacocloudclient.service;

import com.kor4a.tacocloudclient.model.TacoOrder;

public interface OrderMessagingService {

    void sendOrder(TacoOrder order);
}
