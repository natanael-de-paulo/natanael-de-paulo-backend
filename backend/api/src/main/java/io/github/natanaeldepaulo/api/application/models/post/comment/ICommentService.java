package io.github.natanaeldepaulo.api.application.models.post.comment;

public interface ICommentService {
    CommentDTO create(CommentRequest request, String postId, String profileId);
    CommentDTO findById(String postId, String commentId);
    String updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate);
    String deleteCommentToPost(String postId, String commentId);
}
