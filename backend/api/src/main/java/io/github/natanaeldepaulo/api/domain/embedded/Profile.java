package io.github.natanaeldepaulo.api.domain.embedded;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Profile {
    @Id private final UUID id;
    private String name;
    private Boolean image = false;
    private String imageURL;

    public Profile(Profile profile){
        this.id = UUID.randomUUID();
        this.name = profile.name;
        this.image = profile.image;
        this.imageURL = profile.imageURL;
    }

}
