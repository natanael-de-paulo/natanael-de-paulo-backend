package br.java.social_network.application.models.auth;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDTO(
        @NotNull String email,
        @NotNull String password
){}
