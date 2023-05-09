package io.github.natanaeldepaulo.api.domain.embedded;

import lombok.Data;

import java.util.UUID;

@Data
public class Likes {
    private UUID profile_id;
    public Likes(){}
    public Likes(UUID profileId) {
        this.profile_id = profileId;
    }
}
