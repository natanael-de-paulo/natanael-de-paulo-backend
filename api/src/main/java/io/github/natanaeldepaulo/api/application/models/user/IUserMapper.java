package io.github.natanaeldepaulo.api.application.models.user;

import io.github.natanaeldepaulo.api.domain.entities.User;

public interface IUserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
