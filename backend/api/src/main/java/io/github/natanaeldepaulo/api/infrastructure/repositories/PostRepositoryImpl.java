package io.github.natanaeldepaulo.api.infrastructure.repositories;

import io.github.natanaeldepaulo.api.domain.entities.Post;
import io.github.natanaeldepaulo.api.domain.interfaces.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public abstract class PostRepositoryImpl implements IPostRepository {
    @Autowired
    private MongoTemplate _mongoTemplate;

    @Override
    public Post save(Post post) {
        return _mongoTemplate.save(post);
    }

    public Optional<Post> findById(UUID post_id){
        var query = _mongoTemplate.findById(post_id, Post.class);
        return Optional.ofNullable(query);
    }
}
