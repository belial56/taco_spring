package com.kor4a.tacocloudclient.DTO;

import com.kor4a.tacocloudclient.model.Ingredient;

public record IngredientDTO(String id, String name, Ingredient.Type type) {

}
