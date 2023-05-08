package io.github.natanaeldepaulo.api.domain.services;

import io.github.natanaeldepaulo.api.infrastructure.providers.ITokenProvider;
import io.github.natanaeldepaulo.api.application.models.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements UserDetailsService {
    @Autowired
    private IUserService _userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) _userService.findUserByEmail(username);
    }
}
