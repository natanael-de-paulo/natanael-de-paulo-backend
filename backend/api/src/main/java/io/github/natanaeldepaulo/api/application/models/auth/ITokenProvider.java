package io.github.natanaeldepaulo.api.application.models.auth;

import io.github.natanaeldepaulo.api.application.models.user.UserDTO;

public interface ITokenProvider {
    String generateToken(UserDTO user);
}
