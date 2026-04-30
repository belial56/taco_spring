package tacos.service;

import org.springframework.core.annotation.Order;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import tacos.data.OrderRepository;
import tacos.model.TacoOrder;

import java.util.Optional;

@Service
public class OrderService {

    OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    public TacoOrder save(TacoOrder order){
       return orderRepository.save(order);
    }

    public Iterable<TacoOrder> findAll(){
        return orderRepository.findAll();
    }

    public Optional<TacoOrder> findById(Long id){
        return orderRepository.findById(id);
    }

    public void deleteOrder(Long id){
        try {
            orderRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {};
    }
}
