package io.github.natanaeldepaulo.api.infrastructure.repositories;

import io.github.natanaeldepaulo.api.domain.interfaces.IUserRepository;
import io.github.natanaeldepaulo.api.application.services.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Repository;

@Repository
@EnableAsync
public abstract class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private MongoTemplate _mongoTemplate;

    @Override
    public void save(CreateUserRequest user) {
        _mongoTemplate.save(user);
    }

//    @Override
//    public Optional<UserAccount> findById(String _id) {
//        var query = _mongoTemplate.findById(_id, UserAccount.class);
//        return Optional.of(query);
//    }
}
