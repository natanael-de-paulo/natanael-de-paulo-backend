package io.github.natanaeldepaulo.api.infrastructure.mappers;

import io.github.natanaeldepaulo.api.application.models.user.UserDTO;
import io.github.natanaeldepaulo.api.domain.entities.User;

public interface IUserMapper {
    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
}
