package br.java.social_network.application.post.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public record PostRequestDTO (
    String title,
    String description
){
    public PostRequestDTO setTitle(String newTitle) {
        return new PostRequestDTO(newTitle, this.description);
    }

    public PostRequestDTO setDescription(String newDescription) {
        return new PostRequestDTO(this.title, newDescription);
    }
}
