package br.java.social_network.application.models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record UpdatePostRequestDTO(
        @JsonProperty(required = false) String title,
        @JsonProperty(required = false) String description
) {}
