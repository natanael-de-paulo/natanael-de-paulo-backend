package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.models.auth.IAuthService;
import io.github.natanaeldepaulo.api.application.models.auth.AuthRequest;
import io.github.natanaeldepaulo.api.application.models.auth.AuthDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping
    public ResponseEntity<AuthDTO> auth(@RequestBody AuthRequest request) {
        var response = authService.auth(request);
        return ResponseEntity.ok().body(response);
    }
}
