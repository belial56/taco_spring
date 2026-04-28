package com.kor4a.tacocloudclient.DTO;

import com.kor4a.tacocloudclient.model.Ingredient;

import java.util.Date;
import java.util.List;

public record TacoDto (Long id, Date createAt, String name, List<Ingredient> ingredients) {
}
