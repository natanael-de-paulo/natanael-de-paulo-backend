package io.github.natanaeldepaulo.api.application;

import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;

import java.util.List;
import java.util.Optional;

public interface IPostService {
    List<PostResponse> findPosts(String profileId);
    Optional<PostResponse> findPostById(String post_id);
    Optional<PostResponse> createPost(PostRequest post, String profile_id);
    void saveCommentToList(Comment comment, String postId);
    void updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate) throws Exception;

}
