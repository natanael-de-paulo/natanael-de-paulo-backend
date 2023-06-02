package br.java.social_network.application.models.infra_interfaces;

import br.java.social_network.application.models.user.UserDTO;
import io.jsonwebtoken.Claims;

public interface ITokenProvider {
    String generateToken(UserDTO user);
    Claims getClaimsToToken(String token);
}
