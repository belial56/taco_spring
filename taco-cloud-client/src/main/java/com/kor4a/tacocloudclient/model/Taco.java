package com.kor4a.tacocloudclient.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private Long id;
    private Date createdAt = new Date();
    private String name;

    private List<Ingredient> ingredients = new ArrayList<>();

    public void addIngredients(Ingredient ingredient) {
        this.ingredients.add(ingredient);

    }
}
