package br.java.social_network.application.models.infra_interfaces;

import br.java.social_network.application.models.user.UserResponseDTO;
import io.jsonwebtoken.Claims;

public interface ITokenProvider {
    String generateToken(UserResponseDTO user);
    Claims getClaimsToToken(String token);
}
