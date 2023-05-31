package br.java.social_network.application.models.user;

import br.java.social_network.domain.entities.User;

public interface IUserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
