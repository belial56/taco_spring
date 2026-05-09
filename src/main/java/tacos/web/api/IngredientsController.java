package tacos.web.api;

import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tacos.DTO.IngredientDTO;
import tacos.data.IngredientRepository;
import tacos.utils.converter.IngredientMapper;

@RestController
@RequestMapping(path = "/api/ingredients", produces = "application/json")
@CrossOrigin(origins = "localhost:8080")
public class IngredientsController {

    private final IngredientRepository ingredientRepo;
    private final IngredientMapper mapper;

    public IngredientsController(IngredientRepository ingredientRepo, IngredientMapper mapper){
        this.ingredientRepo = ingredientRepo;
        this.mapper = mapper;
    }

    @GetMapping
    public Iterable<IngredientDTO> allIngredients(){
        return ingredientRepo.findAll().stream().map(mapper::toDto).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public IngredientDTO saveIngredient(@RequestBody IngredientDTO ingredient){
        return mapper.toDto(ingredientRepo.save(mapper.toEntity(ingredient)));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteIngredient(@PathVariable("id") String id){
        ingredientRepo.deleteById(id);
    }

}
