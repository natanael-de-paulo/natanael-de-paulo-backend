package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.models.user.IUserService;
import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/user")

public class UserController {
    @Autowired
    private IUserService _userService;

    @GetMapping("/me/{userId}")
    public ResponseEntity<UserDTO> me(@PathVariable String userId){
        var response = _userService.findUserById(userId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<String> createUser(@RequestBody UserDTO request){
        var response = _userService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam(required = false) MultipartFile file){
        var response = _userService.upload(file);
        return ResponseEntity.ok(response);
    }
}
