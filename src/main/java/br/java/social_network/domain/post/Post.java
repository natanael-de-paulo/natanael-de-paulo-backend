package br.java.social_network.domain.post;

import br.java.social_network.domain.post.embedded.Comment;
import br.java.social_network.domain.post.embedded.Likes;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
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
    private UUID user_id;
    private List<Comment> comments;
    private List<Likes> likes;

    private Post(){
        this.id = UUID.randomUUID();
        this.comments = new ArrayList<>();
        this.likes = new ArrayList<>();
    }

    public static Post builder(){
        return new Post();
    }

    public Post title(String title){
        this.title = title;
        return this;
    }

    public Post description(String description){
        this.description = description;
        return this;
    }

    public Post image(Boolean image){
        this.image = image;
        return this;
    }

    public Post imageUrl(String imageUrl){
        this.imageUrl = imageUrl;
        return this;
    }

    public Post userId(UUID userId){
        this.user_id = userId;
        return this;
    }
}
