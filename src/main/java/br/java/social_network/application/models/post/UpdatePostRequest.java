package br.java.social_network.application.models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.Optional;

@Data
public class UpdatePostRequest {
    @JsonProperty(required = false)
    private String title;
    @JsonProperty(required = false)
    private String description;
}
