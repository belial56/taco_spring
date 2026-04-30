package tacos.utils.converter;

import org.mapstruct.Mapper;
import tacos.DTO.OrderDTO;
import tacos.model.TacoOrder;

@Mapper(componentModel = "spring")
public interface TacoOrderMapper {
    TacoOrder toModel(OrderDTO orderDTO);

    OrderDTO toDto(TacoOrder order);
}
