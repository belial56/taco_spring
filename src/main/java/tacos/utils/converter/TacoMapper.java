package tacos.utils.converter;

import tacos.DTO.TacoDTO;
import tacos.model.Taco;

public class TacoMapper {

    public static TacoDTO toDto(Taco taco){
        return new TacoDTO(taco.getId(), taco.getCreatedAt(), taco.getName(), taco.getIngredients());
    }

    public static Taco toEntity(TacoDTO tacoDTO){
        Taco taco = new Taco();
        taco.setId(tacoDTO.id());
        taco.setCreatedAt(tacoDTO.createdAt());
        taco.setName(tacoDTO.name());
        taco.setIngredients(tacoDTO.ingredients());
        return taco;
    }

}
