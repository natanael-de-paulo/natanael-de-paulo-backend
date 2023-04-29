package io.github.natanaeldepaulo.api.domain.embedded;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
public class Profile {

    @Getter
    @Id private UUID id;
    private String name;
    private boolean image = false;
    private String imageURL;

    public Profile(){}

    public Profile(Profile profile){
        this.id = UUID.randomUUID();
        this.name = profile.name;
        this.image = profile.image;
        this.imageURL = profile.imageURL;
    }
}
