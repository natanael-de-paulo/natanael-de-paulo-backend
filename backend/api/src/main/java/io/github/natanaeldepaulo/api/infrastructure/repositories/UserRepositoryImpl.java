package io.github.natanaeldepaulo.api.infrastructure.repositories;

import io.github.natanaeldepaulo.api.domain.entities.User;
import io.github.natanaeldepaulo.api.domain.interfaces.IUserRepository;
import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import org.springframework.data.mongodb.core.query.Query;

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
    public Optional<User> findById(UUID _id) {
        var query = _mongoTemplate.findById(_id, User.class);
        return Optional.of(query);
    }

    @Override
    public User findByEmail(String email){
        Query query = new Query(Criteria.where("email").is(email));
        return _mongoTemplate.findOne(query, User.class);
    }
}
