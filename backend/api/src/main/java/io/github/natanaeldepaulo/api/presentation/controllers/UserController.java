package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.services.CreateUserRequest;
import io.github.natanaeldepaulo.api.application.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @GetMapping
    public String getUser(){
        return "Olá mundo !  ";
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody CreateUserRequest request){
        var response = _userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
