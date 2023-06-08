package br.java.social_network.infrastructure.repositories;

import br.java.social_network.domain.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
    @Query("{user_id: ?0}")
    List<Post> findAll(UUID user_id);
}
