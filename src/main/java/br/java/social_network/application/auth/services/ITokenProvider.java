package br.java.social_network.application.auth.services;

import br.java.social_network.application.user.controllers.response.UserResponseDTO;
import io.jsonwebtoken.Claims;

public interface ITokenProvider {
    String generateToken(UserResponseDTO user);
    Claims getClaimsToToken(String token);
}
