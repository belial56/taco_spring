package tacos.web.api;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tacos.DTO.TacoDTO;
import tacos.data.TacoRepository;
import tacos.utils.converter.TacoMapper;

import java.util.Optional;

@RestController
@RequestMapping(path = "/api/tacos",
                produces = "application/json")
@CrossOrigin(origins = "http://localhost:8080")
public class TacoController {

    private final TacoRepository tacoRepo;
    private final TacoMapper mapper;

    public TacoController(TacoRepository tacoRepo, TacoMapper mapper){
        this.tacoRepo = tacoRepo;
        this.mapper = mapper;
    }

    @GetMapping(params = "recent")
    public Iterable<TacoDTO> recentTacos(){
        PageRequest page = PageRequest.of(0,12,
                Sort.by("CreatedAt").descending());
        return tacoRepo.findAll(page).getContent().stream().map(mapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TacoDTO> tacoById(@PathVariable("id") Long id){
        Optional<TacoDTO> taco = tacoRepo.findById(id).map(t -> mapper.toDto(t));
        if (taco.isPresent()) {
            return new ResponseEntity<>(taco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public TacoDTO postTaco(@RequestBody TacoDTO taco) {
        return mapper.toDto(tacoRepo.save(mapper.toEntity(taco)));
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTaco(@PathVariable("id" ) Long id){
        tacoRepo.deleteById(id);
    }
}
