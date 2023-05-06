
package io.github.natanaeldepaulo.api.application.models.user;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setPassword(String password) {
        this.password = password;
    }

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public String getPassword() {
        return password;
    }
}
