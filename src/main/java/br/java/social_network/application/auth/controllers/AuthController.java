package br.java.social_network.application.auth.controllers;

import br.java.social_network.application.auth.services.IAuthService;
import br.java.social_network.application.auth.controllers.request.AuthRequestDTO;
import br.java.social_network.application.auth.controllers.response.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthService authService;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> execute(@RequestBody AuthRequestDTO request) {
        var response = this.authService.execute(request);
        return ResponseEntity.ok().body(response);
    }
}
