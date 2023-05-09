package io.github.natanaeldepaulo.api.domain.embedded;

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
    private UUID profile_id;
    private List<Likes> likes;
    private Comment(){}

    private Comment(String description, UUID postId, UUID profileId){
        this.id = UUID.randomUUID();
        this.description = description;
        this.post_id = postId;
        this.profile_id = profileId;
        this.likes = new ArrayList<>();
    }

    public static Comment create(String description, UUID postId, UUID profileId ){
        return new Comment(description, postId, profileId);
    }


}
