package io.github.natanaeldepaulo.api.application.dto;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {
    private UUID id;
    public String name;
    public String email;
    public Profile profile;

    public UserResponse(UUID id, String name, String email, Profile profile){
        this.id = id;
        this.name = name;
        this.email = email;
        this.profile = profile;
    }
}
