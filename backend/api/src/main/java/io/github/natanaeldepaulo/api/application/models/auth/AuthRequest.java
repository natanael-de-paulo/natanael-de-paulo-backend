package io.github.natanaeldepaulo.api.application.models.auth;

import lombok.Data;
@Data
public class AuthRequest {
    public String email;
    public String password;
}
