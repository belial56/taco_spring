package tacos.DTO;

import tacos.model.Ingredient;

public record IngredientDTO(String id, String name, Ingredient.Type type){}

