package br.java.social_network.application.user.controllers.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileResponseDTO {
    private String name;
    private Boolean image = false;
    private String imageURL;
    private List<UUID> following;
    private List<UUID> followers;
}
