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

    public TacoOrder save (TacoOrder input, Long id){
        var order = input;
        order.setId(id);
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

    public TacoOrder updateOrder(Long id, TacoOrder patch){
        TacoOrder order = findById(id).get();
        if (patch.getDeliveryName() != null){
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null){
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null){
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null){
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null){
            order.setDeliveryZip(patch.getDeliveryZip());
        }
        if (patch.getCcNumber() != null){
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null){
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null){
            order.setCcCVV(patch.getCcCVV());
        }
        return save(order);

    }
}
