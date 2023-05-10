package io.github.natanaeldepaulo.api.application.models.user;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
    private Profile profile;
}
