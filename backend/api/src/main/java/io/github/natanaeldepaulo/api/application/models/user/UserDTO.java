
package io.github.natanaeldepaulo.api.application.models.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.domain.entities.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserDTO {
    private UUID id;
    private String name;
    @JsonIgnore
    private String password;
    private String email;
    private Profile profile;
}
