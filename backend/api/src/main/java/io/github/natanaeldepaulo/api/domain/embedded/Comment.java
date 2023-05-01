package io.github.natanaeldepaulo.api.domain.embedded;

import io.github.natanaeldepaulo.api.domain.entities.Post;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.UUID;

@Data
public class Comment {
    @Id private final UUID id;
    private String description;
    private UUID post_id;
    private UUID profile_id;
    private ArrayList<Likes> likes;

    public Comment(String description, Post post, Profile profile){
        this.id = UUID.randomUUID();
        this.description = description;
        this.post_id = post.getId();
        this.profile_id = profile.getId();
    }
}
