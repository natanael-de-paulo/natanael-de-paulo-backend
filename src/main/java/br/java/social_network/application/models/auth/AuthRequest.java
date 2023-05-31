package br.java.social_network.application.models.auth;

import lombok.Data;
@Data
public class AuthRequest {
    private String email;
    private String password;
}
