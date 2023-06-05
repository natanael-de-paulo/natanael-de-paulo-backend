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
public class Profile {
    private String name;
    private Boolean image = false;
    private String imageURL;
    private List<UUID> following;
    private List<UUID> followers;

    private Profile(){
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
    }

    public static Profile builder(){
        return new Profile();
    }


    public Profile name(String name){
        this.name = name;
        return this;
    }

    public Profile image(Boolean image, String imageUrl){
        if (!Objects.isNull(imageUrl)) {
            this.imageURL = imageUrl;
            this.image = image;
        }

        return this;
    }
}
