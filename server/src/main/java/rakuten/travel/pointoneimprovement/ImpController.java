package rakuten.travel.pointoneimprovement;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@RestController
public class ImpController {

    private final ImpRepository repository;

    ImpController(ImpRepository repository){
        this.repository = repository;
    }

    @GetMapping("/pointoneimps")
    public @ResponseBody Iterable<Imp> getAllImps() {
        return repository.findAll();
    }

    @GetMapping("/pointoneimps/{id}")
    public @ResponseBody Optional<Imp> getImpById(@PathVariable Integer id) {
        return repository.findById(id);
    }

    @PostMapping("/pointoneimps")
    public Map saveImps(@RequestBody Imp imp) {
        imp.setCreatedAt(LocalDateTime.now());
        repository.save(imp);
        return Collections.singletonMap("id", imp.getId());
    }

    @PatchMapping("/pointoneimps/{id}")
    public Map updateImps(@RequestBody Imp imp, @PathVariable Integer id) {
        repository.findById(id)
                .map(updatedImp -> {
                    updatedImp.setContent(imp.getContent());
                    updatedImp.setCreatedAt(LocalDateTime.now());
                    repository.save(updatedImp);
                    return Collections.singletonMap("id", updatedImp.getId());
                });
        return Collections.singletonMap("id", id);
    }

    @DeleteMapping("/pointoneimps/{id}")
    public Map deleteImps(@PathVariable Integer id){
        repository.deleteById(id);
        return Collections.singletonMap("deleted", "true");
    }
}
