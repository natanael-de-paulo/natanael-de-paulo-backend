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

    private Post(String title, String description, Boolean image, String imageUrl, UUID profileId){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
        this.profile_id = profileId;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public static Post create(String title, String description, Boolean image, String imageUrl, UUID profileId){
        return new Post(title, description, image, imageUrl, profileId);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitle(String title){
        this.title = title;
    }
}
