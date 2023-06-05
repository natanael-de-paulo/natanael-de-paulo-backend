package br.java.social_network.domain.embedded;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class Profile {
    private String name;
    private Boolean image = false;
    private String imageURL;
    private List<UUID> following;
    private List<UUID> followers;

    public static Profile create(Profile input){
        var profile = new Profile();
        profile.setName(input.name);

        if (!Objects.isNull(input.getImageURL())) {
            profile.setImage(true);
            profile.setImageURL(input.imageURL);
        }
        profile.setFollowing(new ArrayList<>());
        profile.setFollowers(new ArrayList<>());

        return profile;
    }

}
