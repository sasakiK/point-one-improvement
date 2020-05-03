package rakuten.travel.pointoneimprovement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuten.travel.pointoneimprovement.dto.IdWapper;
import rakuten.travel.pointoneimprovement.repository.ImpRepository;
import rakuten.travel.pointoneimprovement.dto.Imp;
import rakuten.travel.pointoneimprovement.service.ImpService;

import java.time.LocalDateTime;
import java.util.*;

@RestController
public class ImpController {

    private final ImpRepository repository;

    ImpController(ImpRepository repository){
        this.repository = repository;
    }

    @Autowired
    private ImpService impService;

    @GetMapping("/pointoneimps")
    public ResponseEntity<List> getAllImps(){
        List impList = impService.getAllImps();
        return new ResponseEntity<List>(impList, HttpStatus.OK);
    }

    @GetMapping("/pointoneimps/{id}")
    public ResponseEntity<Imp> getAllImps(@PathVariable Integer id){
        Imp imp = impService.getImpById(id);
        return new ResponseEntity<Imp>(imp, HttpStatus.OK);
    }

    @PostMapping("/pointoneimps")
    public ResponseEntity<IdWapper> saveImp(@RequestBody Imp imp){
        IdWapper idWapper = impService.saveImp(imp);
        return new ResponseEntity<>(idWapper, HttpStatus.OK);
    }

    @PutMapping("/pointoneimps/{id}")
    public ResponseEntity<IdWapper> updateImp(@RequestBody Imp imp,
                                              @PathVariable Integer id) {
        IdWapper idWapper = impService.updateImp(imp, id);
        return new ResponseEntity<>(idWapper, HttpStatus.OK);
    }

    @DeleteMapping("/pointoneimps/{id}")
    public Map<String, Boolean> deleteImpById(@PathVariable Integer id) {
        Boolean deleteResult = impService.deleteImpById(id);
        Map<String, Boolean> map = new HashMap<String, Boolean>() {
            {put("deleted", deleteResult);}
        };
        return map;
    }
}
