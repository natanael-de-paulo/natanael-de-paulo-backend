package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.UserResponse;

public interface IJwtService {
    String generateToken(UserResponse user);
}
