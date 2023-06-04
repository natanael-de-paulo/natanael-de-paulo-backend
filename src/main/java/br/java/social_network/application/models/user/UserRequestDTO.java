package br.java.social_network.application.models.user;

import br.java.social_network.application.models.post.PostRequestDTO;
import br.java.social_network.domain.embedded.Profile;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record UserRequestDTO (
     @NotNull String email,
     @NotNull String password,
     Profile profile
){
    public UserRequestDTO setProfile(Profile inputProfile) {
        return new UserRequestDTO(this.email, this.password, inputProfile);
    }

    public UserRequestDTO setPassword(String password) {
        return new UserRequestDTO(this.email, password, this.profile);
    }

    public UserRequestDTO setEmail(String email) {
        return new UserRequestDTO(email, this.password, this.profile);
    }
}
