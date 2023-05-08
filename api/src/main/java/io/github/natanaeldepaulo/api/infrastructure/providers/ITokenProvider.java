package io.github.natanaeldepaulo.api.infrastructure.providers;

import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.jsonwebtoken.Claims;

public interface ITokenProvider {
    String generateToken(UserDTO user);
    Claims getClaimsToToken(String token);
}
