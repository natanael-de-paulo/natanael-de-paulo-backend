package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import io.github.natanaeldepaulo.api.application.IUserService;
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

    @GetMapping("/{id}")
    public ResponseEntity me(@PathVariable String id){
        var response = _userService.findUserById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest request){
        var response = _userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
