package tacos.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tacos.data.TacoRepository;
import tacos.model.Taco;

import java.util.List;
import java.util.Optional;

@Service
public class TacoService {

    private final TacoRepository repo;


    public TacoService(TacoRepository repo){
        this.repo = repo;
    }

    public List<Taco> recentTacos(){
        PageRequest page = PageRequest.of(0,12,
                Sort.by("CreatedAt").descending());
        return repo.findAll(page).getContent();
    }

    public Optional<Taco> findTacoById(Long id){
        return repo.findById(id);
    }

    public Taco saveTaco(Taco taco){
        return  repo.save(taco);

    }

    public void deleteTaco(Long id){
        repo.deleteById(id);
    }
}
