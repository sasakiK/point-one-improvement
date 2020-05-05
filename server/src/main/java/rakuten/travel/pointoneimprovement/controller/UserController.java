package rakuten.travel.pointoneimprovement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rakuten.travel.pointoneimprovement.dto.IdWapper;
import rakuten.travel.pointoneimprovement.dto.User;
import rakuten.travel.pointoneimprovement.repository.UserRepository;
import rakuten.travel.pointoneimprovement.service.UserService;

@RestController
@RequestMapping("/api/")
public class UserController {

    private final UserRepository repository;

    UserController(UserRepository repository) { this.repository = repository; }

    @Autowired
    private UserService userService;

    @GetMapping("/users/{email}")
    public Integer getUserId(@PathVariable String email) {
        Integer id = userService.getUserId(email);
        return id;
    }

    @PostMapping("/users")
    public ResponseEntity<IdWapper> saveUser(@RequestBody User user) {
        IdWapper idWapper = userService.saveUser(user);
        return new ResponseEntity<>(idWapper, HttpStatus.OK);
    }

}
