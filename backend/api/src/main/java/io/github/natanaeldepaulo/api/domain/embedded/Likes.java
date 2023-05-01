package io.github.natanaeldepaulo.api.domain.embedded;

import java.util.ArrayList;
import java.util.UUID;

public class Likes {
    private UUID profile_id;
    public Likes(Profile profile) {
        this.profile_id = profile.getId();
    }
}
