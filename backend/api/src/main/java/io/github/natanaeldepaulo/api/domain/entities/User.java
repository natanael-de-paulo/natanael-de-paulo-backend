package io.github.natanaeldepaulo.api.domain.entities;

import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.domain.embedded.Profile;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "users")
@Getter
public class User {
    @Id private UUID id;
    private String name;
    private String email;
    private String password;
    private Profile profile;

    private User(){};

    private User(String name, String email, String password, Profile profile){
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = email;
        this.password = password;
        this.profile = profile;
    }

    //using factory method
    public static User create(UserDTO userDTO){
        return new User(
                userDTO.getName(),
                userDTO.getEmail(),
                userDTO.getPassword(),
                userDTO.getProfile()
        );
    }
}
