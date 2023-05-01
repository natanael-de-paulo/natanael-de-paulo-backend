package io.github.natanaeldepaulo.api.infrastructure.repositories;

import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.domain.interfaces.IUserRepository;
import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public abstract class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private MongoTemplate _mongoTemplate;

    @Override
    public void save(UserRequest user) {
        _mongoTemplate.save(user);
    }

    @Override
    public Optional<User> findById(UUID id) {
        User query = _mongoTemplate.findById(id, User.class, "users");
        return Optional.of(query);
    }
}
