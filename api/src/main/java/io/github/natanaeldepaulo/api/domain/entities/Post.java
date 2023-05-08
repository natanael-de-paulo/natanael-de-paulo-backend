package io.github.natanaeldepaulo.api.domain.entities;

import io.github.natanaeldepaulo.api.application.models.post.PostRequest;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.embedded.Likes;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "posts")
@Getter
public class Post {
    @Id private UUID id;
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
    private UUID profile_id;;
    private List<Comment> comments;
    private List<Likes> likes;

    private Post(String title, String description, Boolean image, String imageUrl, UUID profile_id){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
        this.profile_id = profile_id;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public static Post create(PostRequest post, UUID profile_id){
        return new Post(
            post.getTitle(),
            post.getDescription(),
            post.getImage(), post.getImageUrl(),
            profile_id
        );
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
