package tacos.DTO;

import tacos.model.User;

import java.util.Date;
import java.util.LinkedList;
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
    List<TacoDTO> tacos,
    User user
) {
    public OrderDTO(Long id,
                    Date placedAt,
                    String deliveryName,
                    String deliveryStreet,
                    String deliveryCity,
                    String deliveryState,
                    String deliveryZip,
                    String ccNumber,
                    String ccExpiration,
                    String ccCVV,
                    List<TacoDTO> tacos,
                    User user) {
        this.id = id;
        this.placedAt = placedAt;
        this.deliveryName = deliveryName;
        this.deliveryStreet = deliveryStreet;
        this.deliveryCity = deliveryCity;
        this.deliveryState = deliveryState;
        this.deliveryZip = deliveryZip;
        this.ccNumber = ccNumber;
        this.ccExpiration = ccExpiration;
        this.ccCVV = ccCVV;
        this.tacos = tacos;
        this.user = user;
    }
}
