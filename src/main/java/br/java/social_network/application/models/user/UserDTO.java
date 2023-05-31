package br.java.social_network.application.models.user;

import br.java.social_network.domain.embedded.Profile;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String email;
    private Profile profile;
}
