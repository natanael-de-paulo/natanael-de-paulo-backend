package io.github.natanaeldepaulo.api.application.services;

public interface IUserService {
//    Optional<UserResponse> getUserById(String _id);
    String create(CreateUserRequest request);
}
