package br.java.social_network.presentation.controllers.user;

import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/create")
public class CreateUserController {
    @Autowired
    @Qualifier("CreateUserServiceImpl")
    private IUserService<UserRequest, String> userService;

    @PostMapping
    public ResponseEntity<String> handle(@RequestBody UserRequest request){
        var response = this.userService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
