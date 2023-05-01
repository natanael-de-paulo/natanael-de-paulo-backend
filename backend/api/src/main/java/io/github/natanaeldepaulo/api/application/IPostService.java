package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import io.github.natanaeldepaulo.api.domain.entities.Post;

import java.util.Optional;

public interface IPostService {
    Optional<PostResponse> create(PostRequest post, String profile_id);
}
