package io.github.natanaeldepaulo.api.infrastructure.config;

import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.infrastructure.providers.ITokenProvider;
import io.github.natanaeldepaulo.api.infrastructure.repositories.IUserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class FilterToken extends OncePerRequestFilter {

    @Autowired
    private ITokenProvider _tokenProvider;

    @Autowired
    private IUserRepository _userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader != null) {
            var token = authorizationHeader.replace("Bearer ", "");
            var subject = this._tokenProvider.getClaimsToToken(token).getSubject();
            var user = this._userRepository.findByEmail(subject);
            var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }
}
