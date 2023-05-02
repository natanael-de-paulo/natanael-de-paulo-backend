package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.CommentResponse;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;

import java.util.Optional;

public interface ICommentService {
    Optional<CommentResponse> create(CommentRequest request, String postId, String profileId);
    CommentResponse findById(String postId, String commentId);
}
