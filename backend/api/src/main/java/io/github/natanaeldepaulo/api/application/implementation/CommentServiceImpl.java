package io.github.natanaeldepaulo.api.application.implementation;

import io.github.natanaeldepaulo.api.application.ICommentService;
import io.github.natanaeldepaulo.api.application.IPostService;
import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.CommentResponse;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private IPostService postService;

    @Override
    public CommentResponse findById(String postId, String commentId){
        var _commentId = ConvertFormatId.toUUID(commentId);
        List<Comment> comments = postService.findPostById(postId).get().getComments();
        var comment = comments.stream().filter(c -> c.getId().equals(_commentId)).findFirst();
        return new CommentResponse(comment.get());
    }

    @Override
    public CommentResponse create(CommentRequest request, String postId, String profileId){
        var comment = Comment.create(
                request.description,
                ConvertFormatId.toUUID(postId) ,
                ConvertFormatId.toUUID(profileId)
        );

        postService.saveCommentToList(comment, comment.getPost_id().toString());
        return new CommentResponse(comment);
    }

    @Override
    public String updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate){
        try {
            postService.updateCommentToPost(postId, commentId, dataToUpdate);
            return "Updated comment";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteCommentToPost(String postId, String commentId){
        try {
            postService.deleteCommentToPost(postId, commentId);
            return "Deleted comment";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
