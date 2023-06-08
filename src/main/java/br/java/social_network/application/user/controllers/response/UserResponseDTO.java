package br.java.social_network.application.user.controllers.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class UserResponseDTO {
    private UUID id;
    private String email;
    private ProfileResponseDTO profile;
}
