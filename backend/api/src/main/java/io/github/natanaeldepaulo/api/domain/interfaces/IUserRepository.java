package io.github.natanaeldepaulo.api.domain.interfaces;

import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.application.dto.UserRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
    void save(UserRequest _user);
    Optional<User> findById(UUID id);
}
