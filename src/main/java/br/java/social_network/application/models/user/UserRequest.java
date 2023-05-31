package br.java.social_network.application.models.user;

import br.java.social_network.domain.embedded.Profile;
import lombok.Data;

@Data
public class UserRequest {
    private String email;
    private String password;
    private Profile profile;
}
