package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.AuthRequest;
import io.github.natanaeldepaulo.api.application.specification.AuthResponse;
public interface IAuthService {
    AuthResponse auth(AuthRequest request);
}
