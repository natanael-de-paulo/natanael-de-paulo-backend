package br.java.social_network.domain.post.embedded;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Likes {
    private UUID user_id;

    public static Likes builder(){
        return new Likes();
    }

    public Likes createLikeObj(UUID userId) {
        this.user_id = userId;
        return this;
    }
}