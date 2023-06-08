package br.java.social_network.application.post.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public record UpdatePostRequestDTO(
        @JsonProperty(required = false) String title,
        @JsonProperty(required = false) String description
) {}
