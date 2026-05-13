package tacos.utils.converter;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import tacos.DTO.OrderDTO;
import tacos.DTO.TacoDTO;
import tacos.model.Taco;
import tacos.model.TacoOrder;

@Mapper(componentModel = "spring",
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TacoOrderMapper {
    TacoOrder toEntity(OrderDTO orderDTO);

    OrderDTO toDto(TacoOrder order);

    TacoDTO tacoToDto(Taco taco);

    Taco TacoDtoToEntity(TacoDTO taco);

}
