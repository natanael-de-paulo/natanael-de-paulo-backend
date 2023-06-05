package br.java.social_network.presentation.controllers.auth;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.auth.AuthRequestDTO;
import br.java.social_network.application.models.infra_interfaces.ITokenProvider;
import br.java.social_network.application.models.user.UserResponseDTO;
import br.java.social_network.domain.entities.User;
import br.java.social_network.application.models.auth.AuthResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private ITokenProvider tokenProvider;
    @Autowired
    @Qualifier("userMapper")
    private IMapper<User, UserResponseDTO> userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<AuthResponseDTO> auth(@RequestBody AuthRequestDTO request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        );

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authenticate.getPrincipal();
        var token = this.tokenProvider.generateToken(this.userMapper.toDTO(user));
        var response = new AuthResponseDTO();
        response.setToken(token);
        return ResponseEntity.ok().body(response);
    }
}
