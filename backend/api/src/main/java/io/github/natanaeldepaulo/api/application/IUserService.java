package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import io.github.natanaeldepaulo.api.application.specification.UserResponse;

import java.util.Optional;

public interface IUserService {
    Optional<UserResponse> findUserById(String id);
    String create(UserRequest request);
}
