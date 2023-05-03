package io.github.natanaeldepaulo.api.infrastructure.repositories;

import io.github.natanaeldepaulo.api.domain.entities.Post;
import io.github.natanaeldepaulo.api.domain.interfaces.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public abstract class PostRepositoryImpl implements IPostRepository  {
    @Autowired
    private MongoTemplate _mongoTemplate;


    public Post save(Post post) {
        return _mongoTemplate.save(post);
    }


    public Optional<Post> findById(UUID post_id){
        return Optional.ofNullable(_mongoTemplate.findById(post_id, Post.class));
    }

//    public void update(UUID postId, PostRequest dataToUpdate){
//        Query query = new Query(Criteria.where("_id").is(postId));
//        Update update = new Update();
//        update.set("title", dataToUpdate.getTitle());
//        update.set("description", dataToUpdate.getDescription());
//        _mongoTemplate.updateFirst(query, update, Post.class);
//    }
}
