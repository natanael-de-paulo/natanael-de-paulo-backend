package io.github.natanaeldepaulo.api.application.models.auth;

import lombok.Data;
@Data
public class AuthRequest {
    private String email;
    private String password;
}
