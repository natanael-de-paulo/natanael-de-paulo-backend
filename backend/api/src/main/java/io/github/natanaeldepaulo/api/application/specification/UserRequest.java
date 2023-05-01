package io.github.natanaeldepaulo.api.application.dto;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;

@Data
public class UserRequest {
    public String name;
    public String email;
    public String password;
    public Profile profile;
}
