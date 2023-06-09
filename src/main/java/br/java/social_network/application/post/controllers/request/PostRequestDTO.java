package br.java.social_network.application.post.controllers.request;

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
