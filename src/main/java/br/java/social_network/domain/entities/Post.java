package br.java.social_network.domain.entities;

import br.java.social_network.domain.embedded.Comment;
import br.java.social_network.domain.embedded.Likes;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "posts")
@Data
public class Post {
    @Id private UUID id;
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
    private UUID user_id;
    private List<Comment> comments;
    private List<Likes> likes;

    private Post(){}

    private Post(String title, String description, Boolean image, String imageUrl, UUID userId){
        this.id = UUID.randomUUID();
        this.title = title;
        this.description = description;
        this.image = image;
        this.imageUrl = imageUrl;
        this.user_id = userId;
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public static Post create(String title, String description, Boolean image, String imageUrl, UUID userId){
        return new Post(title, description, image, imageUrl, userId);
    }
}
