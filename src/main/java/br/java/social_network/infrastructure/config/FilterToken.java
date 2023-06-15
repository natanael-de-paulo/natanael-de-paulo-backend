package br.java.social_network.infrastructure.config;

import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.providers.interfaces.ITokenProvider;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.io.IOException;
import java.util.Objects;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private ITokenProvider tokenProvider;

    @Autowired
    private IUserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authorizationHeader = request.getHeader("Authorization");

        if(!Objects.isNull(authorizationHeader)) {
            var token = authorizationHeader.replace("Bearer ", "");
            var subject = this.tokenProvider.getClaimsToToken(token).getSubject();
            var user = this.userRepository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
