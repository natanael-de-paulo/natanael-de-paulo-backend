package io.github.natanaeldepaulo.api.application.specification;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.embedded.Likes;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.UUID;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponse {
    private UUID id;
    private String description;
    private UUID post_id;
    private UUID profile_id;
    private List<Likes> likes;


    public CommentResponse(Comment params) {
        this.id = params.getId();
        this.description = params.getDescription();
        this.post_id = params.getPost_id();
        this.profile_id = params.getProfile_id();
        this.likes = params.getLikes();
    }
}
