package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.CommentResponse;

public interface ICommentService {
    CommentResponse create(CommentRequest request, String postId, String profileId);
    CommentResponse findById(String postId, String commentId);
    String updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate);
    String deleteCommentToPost(String postId, String commentId);
}
