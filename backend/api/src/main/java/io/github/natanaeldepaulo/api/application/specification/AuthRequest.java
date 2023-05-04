package io.github.natanaeldepaulo.api.application.specification;

import lombok.Data;
@Data
public class AuthRequest {
    public String email;
    public String password;
}
