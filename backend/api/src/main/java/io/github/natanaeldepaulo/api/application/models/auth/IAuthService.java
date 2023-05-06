package io.github.natanaeldepaulo.api.application.models.auth;

public interface IAuthService {
    AuthDTO auth(AuthRequest request);
}
