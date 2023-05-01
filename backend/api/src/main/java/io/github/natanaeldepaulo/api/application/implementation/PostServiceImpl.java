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
    public Optional<Post> create(PostRequest post, String profile_id){
        UUID _profile_id = UUID.fromString(profile_id);
        var newPost = new Post(
                post.getTitle(),
                post.getDescription(),
                post.getImage(),
                post.getImageUrl(),
                _profile_id
        );

        var createdPost =  _postRepository.save(newPost);

//       var t = createdPost.map(p -> new PostResponse(
//                p.getId(),
//                p.getTitle(),
//                p.getDescription(),
//                p.getImage(),
//                p.getImageUrl(),
//                p.getProfile_id()
//        ));


        return Optional.of(newPost);
    }
}
