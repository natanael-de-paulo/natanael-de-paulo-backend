package br.java.social_network.application.models.post.comment;

public interface ICommentService {
    CommentDTO create(String postId, String userId, CommentRequest request);
    CommentDTO findById(String postId, String commentId);
    String updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate);
    String deleteCommentToPost(String postId, String commentId);
    String likeAndUnlikeComment(String postId, String commentId, String userId);
}
