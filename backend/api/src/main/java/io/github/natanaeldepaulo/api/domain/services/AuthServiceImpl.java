package io.github.natanaeldepaulo.api.domain.services;

import io.github.natanaeldepaulo.api.application.models.auth.IAuthService;
import io.github.natanaeldepaulo.api.application.models.auth.ITokenProvider;
import io.github.natanaeldepaulo.api.application.models.user.IUserService;
import io.github.natanaeldepaulo.api.application.models.auth.AuthRequest;
import io.github.natanaeldepaulo.api.application.models.auth.AuthDTO;
import io.github.natanaeldepaulo.api.infrastructure.mappers.IUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private IUserService _userService;
    @Autowired
    private ITokenProvider _jwtService;
    @Autowired
    private IUserMapper _userMapper;

    @Override
    public AuthDTO auth(AuthRequest request) {
        var user = _userService.findUserByEmail(request.email);

        if (user == null) return null;
        if (!user.getPassword().equals(request.password)) return null;

        var token = _jwtService.generateToken(user);
        var response = new AuthDTO();
        response.setToken(token);
        return response;
    };
}
