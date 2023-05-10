package io.github.natanaeldepaulo.api.domain.embedded;

import lombok.Data;

import java.util.UUID;

@Data
public class Likes {
    private UUID user_id;
    public Likes(){}
    public Likes(UUID userId) {
        this.user_id = userId;
    }
}
