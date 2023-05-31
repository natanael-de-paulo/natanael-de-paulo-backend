package br.java.social_network.domain.services;

import br.java.social_network.domain.embedded.Comment;
import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.comment.CommentDTO;
import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private IPostService _postService;

    @Override
    public CommentDTO findById(String postId, String commentId){
        List<Comment> comments = _postService.findPostById(postId).getComments();
        var comment = comments.stream().filter(c -> c.getId().equals(ConvertFormatId.toUUID(commentId))).findFirst();
        return new CommentDTO(comment.get());
    }

    @Override
    public CommentDTO create(String postId, String userId, CommentRequest request){
        var comment = Comment.create(
                request.description,
                ConvertFormatId.toUUID(postId) ,
                ConvertFormatId.toUUID(userId)
        );

        _postService.saveCommentToList(comment, comment.getPost_id().toString());
        return new CommentDTO(comment);
    }

    @Override
    public String likeAndUnlikeComment(String postId, String commentId, String userId){
        try {
            var response = _postService.likeAndUnlikeCommentToPost(postId, commentId, userId);
            return response;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }


    @Override
    public String updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate){
        try {
            _postService.updateCommentToPost(postId, commentId, dataToUpdate);
            return "Updated comment";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public String deleteCommentToPost(String postId, String commentId){
        try {
            _postService.deleteCommentToPost(postId, commentId);
            return "Deleted comment";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
