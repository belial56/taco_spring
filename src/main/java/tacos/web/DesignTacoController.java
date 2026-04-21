package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.model.Taco;
import tacos.model.TacoOrder;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;

    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo
    ){
        this.ingredientRepo = ingredientRepo;
    }

    @ModelAttribute
    public void  addIngredientsToModel(Model model){
        Iterable<Ingredient> ingredients = ingredientRepo.findAll();

        Type[] types = Ingredient.Type.values();

        for (var type : types){
            model.addAttribute(type.toString().toLowerCase(),
            filterByType((List<Ingredient>) ingredients, type));
        }
    }

    @ModelAttribute("tacoOrder")
    public TacoOrder order(){
        return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors error,
                              @ModelAttribute TacoOrder tacoOrder){
        if (error.hasErrors()){
            return "design";
        }
        tacoOrder.addTaco(taco);
        log.info("Processing taco: {}", taco);
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType( List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
