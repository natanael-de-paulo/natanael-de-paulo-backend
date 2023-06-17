package br.java.social_network.infrastructure.services;

import br.java.social_network.application.auth.controllers.request.AuthRequestDTO;
import br.java.social_network.application.auth.controllers.response.AuthResponseDTO;
import br.java.social_network.application.auth.services.IAuthService;
import br.java.social_network.application.user.controllers.response.UserResponseDTO;
import br.java.social_network.domain.user.User;
import br.java.social_network.infrastructure.mappers.IMapper;
import br.java.social_network.application.auth.services.ITokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private ITokenProvider tokenProvider;
    @Autowired
    @Qualifier("userMapper")
    private IMapper<User, UserResponseDTO> userMapper;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO execute(AuthRequestDTO request) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                = new UsernamePasswordAuthenticationToken(
                request.email(), request.password()
        );

        Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        var user = (User) authenticate.getPrincipal();
        var token = this.tokenProvider.generateToken(this.userMapper.toDTO(user));
        var response = new AuthResponseDTO(token);
        return response;
    }
}
