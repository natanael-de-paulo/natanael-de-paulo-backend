package io.github.natanaeldepaulo.api.domain.entities;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
@Data
public class User {
    @Id private final UUID id;
    private String name;
    private String email;
    private String password;
    private Profile profile;

    public User(String name, String email, String password, Profile profile){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }
}
