package tacos.utils.converter;

import org.mapstruct.Mapper;
import tacos.DTO.TacoDTO;
import tacos.model.Taco;

@Mapper(componentModel = "spring")
public interface TacoMapper {

    Taco toEntity(TacoDTO taco);

    TacoDTO toDto(Taco taco);

}
