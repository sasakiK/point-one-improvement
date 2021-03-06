package rakuten.travel.pointoneimprovement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuten.travel.pointoneimprovement.dto.IdWapper;
import rakuten.travel.pointoneimprovement.dto.Imp;
import rakuten.travel.pointoneimprovement.repository.ImpRepository;

import java.util.List;
import java.util.Map;

@Service
public class ImpService {

    @Autowired
    private ImpRepository impRepository;

    public List<Map<String, Object>> getAllImps() {
        return impRepository.getAllImps();
    }

    public List<Map<String, Object>> getImpsByEmail(String email) {
        return impRepository.getImpsByEmail(email);
    }

    public IdWapper saveImp(Imp imp, String email) {
        Integer id = impRepository.saveImp(imp, email);
        return IdWapper.builder().id(id).build();
    }

    public IdWapper updateImp(Imp imp, Integer id) {
        Integer updatedId = impRepository.updateImp(imp, id);
        return IdWapper.builder().id(updatedId).build();
    }

    public boolean deleteImpById(Integer id) {
        return impRepository.deleteImpById(id);
    }
}