package br.java.social_network.infrastructure.config;

import br.java.social_network.application.auth.services.ITokenProvider;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
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
        try {
            String jwt = parseJwt(request);

            if (!Objects.isNull(jwt)) {
                var token = jwt.replace("Bearer ", "");
                var subject = this.tokenProvider.getClaimsToToken(token).getSubject();
                var user = this.userRepository.findByEmail(subject);
                var authentication = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (JwtException e) {
             throw new ServletException("Invalid token");
        } catch (NullPointerException e) {
             response.setStatus(HttpStatus.BAD_REQUEST.value());
             response.getWriter().write("Invalid request");
        } catch (Exception e) {
             response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
             response.getWriter().write("Internal server error");
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7);
        }

        return null;
    }
}
