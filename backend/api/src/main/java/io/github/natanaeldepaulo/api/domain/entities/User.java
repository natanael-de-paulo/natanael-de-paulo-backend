package io.github.natanaeldepaulo.api.domain.entities;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
@Data
public class User {
    @Getter
    @Id private final UUID id;
    private String name;
    private String email;
    private String password;
    private Profile profile;

    public User(String _name, String _email, String _password, Profile _profile){
        this.id = UUID.randomUUID();
        this.name = _name;
        this.email = _email;
        this.password = _password;
        this.profile = _profile;
    }
}
