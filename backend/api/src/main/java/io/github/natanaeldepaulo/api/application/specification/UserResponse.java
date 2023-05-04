
package io.github.natanaeldepaulo.api.application.specification;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import io.github.natanaeldepaulo.api.domain.entities.User;
import lombok.Data;
import java.util.UUID;

@Data
public class UserResponse {
    public UUID id;
    public String name;
    public String email;
    public Profile profile;

    public UserResponse(){}

    public UserResponse(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.profile = user.getProfile();
    }
}
