package rakuten.travel.pointoneimprovement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rakuten.travel.pointoneimprovement.controller.UserController;
import rakuten.travel.pointoneimprovement.dto.IdWapper;
import rakuten.travel.pointoneimprovement.dto.User;
import rakuten.travel.pointoneimprovement.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Integer getUserId(String email) {
        return userRepository.getUserId(email);
    }

    public IdWapper saveUser(User user) {
        Integer id = userRepository.saveUser(user);
        return IdWapper.builder().id(id).build();
    }
}
