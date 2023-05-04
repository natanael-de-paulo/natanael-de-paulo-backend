package io.github.natanaeldepaulo.api.domain.embedded;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
public class Comment {
    @Id private UUID id;
    @Setter private String description;
    private UUID post_id;
    private UUID profile_id;
    private List<Likes> likes;


    public Comment(){}

    private Comment(String description, UUID post_id, UUID profile_id){
        this.id = UUID.randomUUID();
        this.description = description;
        this.post_id = post_id;
        this.profile_id = profile_id;
        this.likes = new ArrayList<>();
    }

    public static Comment create(String description, UUID post_id, UUID profile_id ){
        return new Comment(description, post_id, profile_id);
    }


}
