package tacos.DTO;

import tacos.model.Taco;
import tacos.model.User;

import java.util.Date;
import java.util.List;

public record OrderDTO(
    Long id,
    Date placedAt,
    String deliveryName,
    String deliveryStreet,
    String deliveryCity,
    String deliveryState,
    String deliveryZip,
    String ccNumber,
    String ccExpiration,
    String ccCVV,
    List<Taco> tacos,
    User user
){}
