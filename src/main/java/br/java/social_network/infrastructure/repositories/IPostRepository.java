package br.java.social_network.infrastructure.repositories;

import br.java.social_network.domain.post.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.UUID;

public interface IPostRepository extends MongoRepository<Post, UUID> {
    @Query("{user_id: ?0}")
    List<Post> findAll(UUID user_id);

    @Query("[{ $match: { 'user_id': { $in: ?0 } } }, { $sort: { 'createdAt': -1 } }, { $group: { '_id': '$user_id', 'latestPost': { $first: '$$ROOT' } } }, { $replaceRoot: { 'newRoot': '$latestPost' } }]")
    List<Post> findPostsByFollowingProfiles(List<UUID> followingProfiles);
}
