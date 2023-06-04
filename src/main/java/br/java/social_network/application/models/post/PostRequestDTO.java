package br.java.social_network.application.models.post;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.web.multipart.MultipartFile;

public record PostRequestDTO (
    String title,
    String description,
    @JsonProperty(required = false) MultipartFile file
){
    public PostRequestDTO setTitle(String newTitle) {
        return new PostRequestDTO(newTitle, this.description, this.file);
    }

    public PostRequestDTO setDescription(String newDescription) {
        return new PostRequestDTO(this.title, newDescription, this.file);
    }

    public PostRequestDTO setFile(MultipartFile newFile) {
        return new PostRequestDTO(this.title, this.description, newFile);
    }
}
