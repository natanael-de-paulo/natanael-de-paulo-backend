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
    public Optional<PostResponse> findById(String post_id){
        var postId = UUID.fromString(post_id);
        var post = _postRepository.findById(postId);

        var response = new PostResponse(
                post.get().getId(),
                post.get().getTitle(),
                post.get().getDescription(),
                post.get().getImage(),
                post.get().getImageUrl(),
                post.get().getProfile_id(),
                post.get().getComments(),
                post.get().getLikes()
        );

        return Optional.of(response);
    }

    @Override
    public Optional<PostResponse> create(PostRequest request, String profile_id){
        var profileId = UUID.fromString(profile_id);
        var post = Post.create(request, profileId);
        _postRepository.save(post);

        var response = new PostResponse(
            post.getId(),
            post.getTitle(),
            post.getDescription(),
            post.getImage(),
            post.getImageUrl(),
            post.getProfile_id(),
            post.getComments(),
            post.getLikes()
        );

        return Optional.of(response);
    }
}
