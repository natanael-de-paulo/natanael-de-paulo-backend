package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import io.github.natanaeldepaulo.api.application.specification.UserResponse;
import io.github.natanaeldepaulo.api.domain.entities.User;

import java.util.Optional;

public interface IUserService {
    UserResponse findUserById(String userId);
    String create(UserRequest request);
    User findUserByEmail(String email);
}
