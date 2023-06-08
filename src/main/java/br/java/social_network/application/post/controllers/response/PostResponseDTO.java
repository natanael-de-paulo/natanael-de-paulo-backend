package br.java.social_network.application.post.controllers.response;

import br.java.social_network.domain.post.embedded.Comment;
import br.java.social_network.domain.post.embedded.Likes;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostResponseDTO {
    private UUID id;
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
    private UUID user_id;
    private List<Comment> comments;
    private List<Likes> likes;
}
