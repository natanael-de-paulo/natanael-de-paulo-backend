package br.java.social_network.application.comment.controllers.response;

import br.java.social_network.domain.post.embedded.Comment;
import br.java.social_network.domain.post.embedded.Likes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentResponseDTO {
    private UUID id;
    private String description;
    private UUID post_id;
    private UUID user_id;
    private List<Likes> likes;

    public CommentResponseDTO(Comment params) {
        this.id = params.getId();
        this.description = params.getDescription();
        this.post_id = params.getPost_id();
        this.user_id = params.getUser_id();
        this.likes = params.getLikes();
    }
}
