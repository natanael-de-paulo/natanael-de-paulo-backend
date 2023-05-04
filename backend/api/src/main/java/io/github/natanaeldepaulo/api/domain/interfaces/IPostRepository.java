package io.github.natanaeldepaulo.api.domain.interfaces;

import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
    @Query("{profile_id: ?0}")
    List<Post> findAll(UUID profile_id);
}
