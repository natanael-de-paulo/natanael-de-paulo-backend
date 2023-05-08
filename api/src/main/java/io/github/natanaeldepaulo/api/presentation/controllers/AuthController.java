package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.models.auth.IAuthService;
import io.github.natanaeldepaulo.api.application.models.auth.AuthRequest;
import io.github.natanaeldepaulo.api.application.models.auth.AuthDTO;
import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.infrastructure.mappers.IUserMapper;
import io.github.natanaeldepaulo.api.infrastructure.providers.ITokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1")
public class AuthController {
    @Autowired
    private ITokenProvider _tokenProvider;

    @Autowired
    private IUserMapper _userMapper;
    @Autowired
    private AuthenticationManager _authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<AuthDTO> auth(@RequestBody AuthRequest request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(
                        request.getEmail(), request.getPassword()
        );

        Authentication authenticate = this._authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authenticate.getPrincipal();
        var token = _tokenProvider.generateToken(_userMapper.toDTO(user));

        var response = new AuthDTO();
        response.setToken(token);
        return ResponseEntity.ok().body(response);
    }
}
