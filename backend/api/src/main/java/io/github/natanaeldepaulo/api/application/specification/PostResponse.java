package io.github.natanaeldepaulo.api.application.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.embedded.Likes;
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

    public PostResponse(UUID id, String title, String description, Boolean image, String imageUrl, UUID profile_id, List<Comment> comments, List<Likes> likes) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
        this.profile_id = profile_id;
        this.comments = comments;
        this.likes = likes;
    }
}
