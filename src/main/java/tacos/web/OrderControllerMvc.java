package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import tacos.DTO.OrderDTO;
import tacos.data.OrderRepository;
import tacos.model.TacoOrder;
import tacos.model.User;
import tacos.utils.converter.TacoOrderMapper;

import java.util.Date;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderControllerMvc {

    private final OrderRepository orderRepo;
    private final TacoOrderMapper orderMapper;

    public OrderControllerMvc(OrderRepository orderRepo, TacoOrderMapper orderMapper) {
        this.orderRepo = orderRepo;
        this.orderMapper = orderMapper;
    }

    @GetMapping("/current")
    public String orderForm(){
        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid OrderDTO orderDto, Errors error,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user){

        if(error.hasErrors()){
            System.out.println(error);
            return "orderForm";
        }

        TacoOrder order = orderMapper.toEntity(orderDto);

        order.setPlacedAt(new Date());
        order.setUser(user);

        orderRepo.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete();

        return "redirect:/";
    }
}
