package br.java.social_network.application.models.auth;

public interface IAuthService {
    AuthResponseDTO auth(AuthRequestDTO request);
}
