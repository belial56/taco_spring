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
    public OrderDTO postOrder(@RequestBody OrderDTO order){
        return  mapper.toDto(orderService.save(mapper.toEntity(order)));
    }

    @PutMapping(path = "/{id}", consumes = "application/json")
    public OrderDTO putOrder(
            @PathVariable("id") Long id,
            @RequestBody OrderDTO order){

        return mapper.toDto(orderService.save(mapper.toEntity(order), id));
    }

    @PatchMapping(path = "/{id}", consumes = "application/json")
    public OrderDTO patchOrder(
            @PathVariable("id") Long id,
            @RequestBody OrderDTO patch
    ){
        return mapper.toDto(
                orderService.updateOrder(id, mapper.toEntity(patch))
        );
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("id") Long id){
        orderService.deleteOrder(id);
    }
}


