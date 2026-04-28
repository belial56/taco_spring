package com.kor4a.tacocloudclient.service;

import com.kor4a.tacocloudclient.model.Ingredient;

public interface IngredientService {

    Iterable<Ingredient> findAll();

    Ingredient addIngredient(Ingredient ingredient);

}
