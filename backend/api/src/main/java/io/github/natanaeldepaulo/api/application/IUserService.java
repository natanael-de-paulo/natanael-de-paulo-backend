package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.dto.UserRequest;
import io.github.natanaeldepaulo.api.application.dto.UserResponse;

import java.util.Optional;

public interface IUserService {
    Optional<UserResponse> findUserById(String id);
    String create(UserRequest request);
}
