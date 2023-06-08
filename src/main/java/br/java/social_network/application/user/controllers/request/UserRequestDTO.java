package br.java.social_network.application.user.controllers.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserRequestDTO (
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must have at least 8 characters")
        String password,

        ProfileRequestDTO profile
){
    public UserRequestDTO setProfile(ProfileRequestDTO inputProfile) {
        return new UserRequestDTO(this.email, this.password, inputProfile);
    }

    public UserRequestDTO setPassword(String password) {
        return new UserRequestDTO(this.email, password, this.profile);
    }

    public UserRequestDTO setEmail(String email) {
        return new UserRequestDTO(email, this.password, this.profile);
    }
}
