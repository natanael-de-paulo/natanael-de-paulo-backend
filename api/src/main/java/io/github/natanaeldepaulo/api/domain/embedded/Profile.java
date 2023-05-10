package io.github.natanaeldepaulo.api.domain.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Profile {
    private String name;
    private Boolean image = false;
    private String imageURL;
    private List<UUID> following;
    private List<UUID> followers;

    private Profile(){}

    private Profile(Profile profile){
        this.name = profile.name;
        this.image = profile.image;
        this.imageURL = profile.imageURL;
        this.following = new ArrayList<>();
        this.followers = new ArrayList<>();
    }

    public static Profile create(Profile profile){
        return new Profile(profile);
    }

}
