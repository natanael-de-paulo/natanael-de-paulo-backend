package io.github.natanaeldepaulo.api.infrastructure.repositories;

import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.UUID;

public interface ICommentRepository extends MongoRepository<Post, UUID> {
}
