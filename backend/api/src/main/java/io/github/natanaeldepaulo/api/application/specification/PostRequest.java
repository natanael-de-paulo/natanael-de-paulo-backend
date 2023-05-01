package io.github.natanaeldepaulo.api.application.specification;

import lombok.Data;

import java.util.UUID;

@Data
public class PostRequest {
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
}
