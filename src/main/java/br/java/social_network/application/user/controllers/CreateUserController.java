package br.java.social_network.application.user.controllers;

import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.application.user.controllers.request.UserRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@RequestMapping("/users")
public class CreateUserController {
    @Autowired
    @Qualifier("CreateUserServiceImpl")
    private IUserService<UserRequestDTO, String> userService;

    @PostMapping
    public ResponseEntity<String> handle(@RequestBody UserRequestDTO request){
        var response = this.userService.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
