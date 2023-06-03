package br.java.social_network.domain.embedded;

import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Comment {
    @Id private UUID id;
    @Setter private String description;
    private UUID post_id;
    private UUID user_id;
    private List<Likes> likes;

    private Comment(){
        this.id = UUID.randomUUID();
        this.likes = new ArrayList<>();
    }
    public static Comment builder(){
        return new Comment();
    }

    public Comment description(String description){
        this.description = description;
        return this;
    }

    public Comment postId(UUID postId){
        this.post_id = postId;
        return this;
    }

    public Comment user_id(UUID user_id){
        this.user_id = user_id;
        return this;
    }
}
