package io.github.natanaeldepaulo.api.domain.interfaces;

import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.UserRequest;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Optional;
import java.util.UUID;


public interface IPostRepository extends MongoRepository<Post, UUID> {
    Post save(Post post);
    Optional<Post> findById(UUID post_id);
}
