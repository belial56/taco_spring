package tacos.utils.converter;

import org.mapstruct.Mapper;
import tacos.DTO.IngredientDTO;
import tacos.model.Ingredient;

@Mapper(componentModel = "spring")
public interface IngredientMapper {

    Ingredient toEntity(IngredientDTO ingredient);

    IngredientDTO toDto(Ingredient ingredient);
}
