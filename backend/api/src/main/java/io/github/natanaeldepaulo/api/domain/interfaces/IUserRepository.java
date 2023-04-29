package io.github.natanaeldepaulo.api.domain.interfaces;

import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.application.services.CreateUserRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
    void save(CreateUserRequest _user);
//    Optional<User> findById(String _id);
}
