package tacos.service;

import org.springframework.stereotype.Service;
import tacos.data.IngredientRepository;
import tacos.model.Ingredient;

import java.util.List;

@Service
public class IngredientService {

    private final IngredientRepository repo;

    public IngredientService(IngredientRepository repo){
        this.repo = repo;
    }

    public List<Ingredient> allIngredients(){
        return repo.findAll();
    }

    public Ingredient saveIngredient(Ingredient ingredient){
        return repo.save(ingredient);
    }

    public void deleteIngredient(String id){
        repo.deleteById(id);
    }



}
