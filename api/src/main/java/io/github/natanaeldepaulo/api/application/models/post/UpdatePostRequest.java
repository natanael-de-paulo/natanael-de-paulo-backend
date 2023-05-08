package io.github.natanaeldepaulo.api.application.models.post;

import lombok.Data;
import java.util.Optional;

@Data
public class UpdatePostRequest {
    private Optional<String> title;
    private Optional<String> description;
}
