package io.github.natanaeldepaulo.api.application.services;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;

@Data
public class CreateUserRequest {
    public String name;
    public String email;
    public String password;
    public Profile profile;
}
