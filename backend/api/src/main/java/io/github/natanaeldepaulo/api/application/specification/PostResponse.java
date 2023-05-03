package io.github.natanaeldepaulo.api.application.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.embedded.Likes;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponse {
    private final UUID id;
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
    private final UUID profile_id;
    private List<Comment> comments;
    private List<Likes> likes;

    public PostResponse(Post params) {
        this.id = params.getId();
        this.title = params.getTitle();
        this.description = params.getDescription();
        this.image = params.getImage();
        this.imageUrl = params.getImageUrl();
        this.profile_id = params.getProfile_id();
        this.comments = params.getComments();
        this.likes = params.getLikes();
    }
}
