package tacos.service;


import org.springframework.stereotype.Component;
import tacos.data.OrderRepository;

@Component
public class AdminService {
    private final OrderRepository orderRepository;

    AdminService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public void deleteAllOrders(){
        orderRepository.deleteAll();
    }

}
