package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.dto.UserRequest;
import io.github.natanaeldepaulo.api.application.IUserService;
import io.github.natanaeldepaulo.api.application.dto.UserResponse;
import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.domain.interfaces.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    private IUserService _userService;

    @GetMapping("/{id}")
    public ResponseEntity me(@PathVariable String id){
        Optional<UserResponse> response = _userService.findUserById(id);
        var result = response.map( u -> new UserResponse(u.getId(), u.getName(), u.getEmail(), u.getProfile()));

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserRequest request){
        var response = _userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
