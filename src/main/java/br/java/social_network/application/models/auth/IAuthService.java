package br.java.social_network.application.models.auth;

public interface IAuthService {
    AuthDTO auth(AuthRequest request);
}
