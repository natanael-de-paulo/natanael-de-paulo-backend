package br.java.social_network.application.auth.services;

import br.java.social_network.application.auth.controllers.request.AuthRequestDTO;
import br.java.social_network.application.auth.controllers.response.AuthResponseDTO;

public interface IAuthService {
    AuthResponseDTO execute(AuthRequestDTO request);
}
