package br.java.social_network.application.user.controllers.request;

public record ProfileRequestDTO(String name, Boolean image, String imageURL) {

    public ProfileRequestDTO setProfileName(String name) {
        return new ProfileRequestDTO(name, this.image, this.imageURL);
    }

    public ProfileRequestDTO setProfileImage(Boolean image) {
        return new ProfileRequestDTO(this.name, image, this.imageURL);
    }

    public ProfileRequestDTO setProfileImageURL(String imageURL) {
        return new ProfileRequestDTO(this.name, this.image, imageURL);
    }

}
