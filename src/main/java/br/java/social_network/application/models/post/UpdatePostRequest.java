package br.java.social_network.application.models.post;

import lombok.Data;
import java.util.Optional;

@Data
public class UpdatePostRequest {
    private Optional<String> title;
    private Optional<String> description;
}
