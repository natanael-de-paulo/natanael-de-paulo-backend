package io.github.natanaeldepaulo.api.application.implementation;

import io.github.natanaeldepaulo.api.application.IPostService;
import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import io.github.natanaeldepaulo.api.domain.interfaces.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostRepository _postRepository;

    @Override
    public Optional<PostResponse> create(PostRequest post, String profile_id){
        UUID _profile_id = UUID.fromString(profile_id);

        var newPost = new Post(
                post.getTitle(),
                post.getDescription(),
                post.getImage(),
                post.getImageUrl(),
                _profile_id
        );

        var createdPost =  _postRepository.save(newPost);

        var response = new PostResponse(
                createdPost.getId(),
                createdPost.getTitle(),
                createdPost.getDescription(),
                createdPost.getImage(),
                createdPost.getImageUrl(),
                createdPost.getProfile_id(),
                createdPost.getComments(),
                createdPost.getLikes()
        );

        return Optional.of(response);
    }
}
