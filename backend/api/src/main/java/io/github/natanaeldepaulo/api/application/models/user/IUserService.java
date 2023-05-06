package io.github.natanaeldepaulo.api.application.models.user;
import io.github.natanaeldepaulo.api.domain.entities.User;

public interface IUserService {
    UserDTO findUserById(String userId);
    String create(UserDTO request);
    UserDTO findUserByEmail(String email);
}
