package tacos.DTO;

import tacos.model.Ingredient;

import java.util.Date;
import java.util.List;

public record TacoDTO(Long id, Date createdAt, String name, List<Ingredient> ingredients){
}
