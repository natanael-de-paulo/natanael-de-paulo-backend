package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;

import java.util.Optional;

public interface IPostService {
    Optional<PostResponse> create(PostRequest post, String profile_id);
    Optional<PostResponse> findById(String post_id);
    void update(PostRequest dataToUpdate, String postId);
}
