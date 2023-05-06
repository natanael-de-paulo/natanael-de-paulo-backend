package io.github.natanaeldepaulo.api.application.models.post;

import lombok.Data;
@Data
public class PostRequest {
    private String title;
    private String description;
    private Boolean image = false;
    private String imageUrl;
}
