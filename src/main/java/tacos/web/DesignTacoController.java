package tacos.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.DTO.OrderDTO;
import tacos.DTO.TacoDTO;
import tacos.model.Ingredient;
import tacos.model.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.model.Taco;
import tacos.model.TacoOrder;
import tacos.utils.converter.TacoMapper;
import tacos.utils.converter.TacoOrderMapper;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private final TacoMapper tacoMapper;
    private final TacoOrderMapper orderMapper;


    @Autowired
    public DesignTacoController(
            IngredientRepository ingredientRepo,
            TacoMapper tacoMapper,
            TacoOrderMapper orderMapper
    ){
        this.ingredientRepo = ingredientRepo;
        this.tacoMapper = tacoMapper;
        this.orderMapper = orderMapper;
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
    public OrderDTO order(){
        return orderMapper.toDto(new TacoOrder());
    }

    @ModelAttribute(name = "taco")
    public TacoDTO taco(){
        return tacoMapper.toDto(new Taco());
    }

    @GetMapping
    public String showDesignForm(){
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid TacoDTO taco, Errors error,
                              @ModelAttribute OrderDTO tacoOrder){
        if (error.hasErrors()){
            return "design";
        }
        TacoOrder order = orderMapper.toEntity(tacoOrder);
        order.addTaco(tacoMapper.toEntity(taco));
        log.info("Processing taco: {}", taco.id());
        return "redirect:/orders/current";
    }

    private Iterable<Ingredient> filterByType( List<Ingredient> ingredients, Ingredient.Type type){
        return ingredients.stream().filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

}
