package io.github.natanaeldepaulo.api.domain.interfaces;

import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
    void save(PostRequest post);
}
