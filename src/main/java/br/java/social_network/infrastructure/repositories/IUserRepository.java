package br.java.social_network.infrastructure.repositories;

import br.java.social_network.application.user.controllers.response.UserResponseDTO;
import br.java.social_network.domain.user.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends MongoRepository<User, UUID> {
    void save(UserResponseDTO user);
    Optional<User> findById(UUID userId);
    @Query("{email: ?0}")
    User findByEmail(String email);
}
