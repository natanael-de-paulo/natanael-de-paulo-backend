package io.github.natanaeldepaulo.api.domain.entities;

import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "users")
@Data
public class User {
    @Getter
    @Id private UUID id;
    @Field private String name;
    @Field private String email;
    @Field private String password;
    @Field private Profile profile;

    public User(String name, String email, String password, Profile profile){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }
}
