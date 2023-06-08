package br.java.social_network.application.auth.controllers.request;

import jakarta.validation.constraints.NotNull;

public record AuthRequestDTO(
        @NotNull String email,
        @NotNull String password
){}
