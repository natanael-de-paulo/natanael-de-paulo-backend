package io.github.natanaeldepaulo.api.application.models.post;

import io.github.natanaeldepaulo.api.application.models.post.comment.CommentRequest;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;

import java.util.List;

public interface IPostService {
    List<PostDTO> findPosts(String profileId);
    PostDTO findPostById(String post_id);
    PostDTO createPost(PostRequest post, String profile_id);
    void updatePost(String postId, UpdatePostRequest dataToUpdate) throws Exception;
    void deletePost(String postId) throws Exception;
    void saveCommentToList(Comment comment, String postId);
    void updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate) throws Exception;
    void deleteCommentToPost(String postId, String commentId) throws Exception;
}
