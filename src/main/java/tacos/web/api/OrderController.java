package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.DTO.OrderDTO;
import tacos.model.TacoOrder;
import tacos.service.OrderService;
import tacos.utils.converter.TacoOrderMapper;

import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final TacoOrderMapper mapper;

    public OrderController(OrderService orderService, TacoOrderMapper mapper){
        this.orderService = orderService;
        this.mapper = mapper;
    }

    @GetMapping(path = "/{id}",consumes = "application/json")
    public ResponseEntity<OrderDTO> getOrder(@PathVariable("id") Long id){

        Optional<TacoOrder> order = orderService.findById(id);
        if (order.isPresent()){
            return new ResponseEntity<OrderDTO>(mapper.toDto(order.get()), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoOrder postOrder(@RequestBody TacoOrder order){
        return  orderService.save(order);
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public TacoOrder putOrder(
            @PathVariable("id") Long id,
            @RequestBody TacoOrder order){
        order.setId(id);
        return orderService.save(order);
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public TacoOrder patchOrder(
            @PathVariable("id") Long id,
            @RequestBody TacoOrder patch
    ){
        TacoOrder order = orderService.findById(id).get();
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
        return orderService.save(order);
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }
}


