package io.github.natanaeldepaulo.api.application.models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PostRequest {
    private String title;
    private String description;
    @JsonProperty(required = false)
    private MultipartFile file;
}
