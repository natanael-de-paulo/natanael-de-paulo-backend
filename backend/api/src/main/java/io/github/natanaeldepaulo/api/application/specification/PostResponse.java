package io.github.natanaeldepaulo.api.application.specification;

import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.embedded.Likes;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class PostResponse {
    private UUID id;
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
    private UUID profile_id;


    public PostResponse(UUID id, String title, String description, Boolean image, String imageUrl, UUID profile_id) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
        this.profile_id = profile_id;
    }
}
