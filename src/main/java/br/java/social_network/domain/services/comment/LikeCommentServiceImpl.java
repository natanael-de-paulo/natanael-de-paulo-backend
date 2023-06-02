package br.java.social_network.domain.services.comment;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.comment.CommentDTO;
import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("LikeCommentServiceImpl")
public class LikeCommentServiceImpl implements ICommentService {
    @Autowired
    @Qualifier("LikeCommentToListPost")
    private IPostService postService;

    @Override
    public CommentDTO create(String postId, String userId, CommentRequest request) {
        return null;
    }

    @Override
    public CommentDTO findById(String postId, String commentId) {
        return null;
    }

    @Override
    public String updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate) {
        return null;
    }

    @Override
    public String deleteCommentToPost(String postId, String commentId) {
        return null;
    }

    @Override
    public String likeAndUnlikeComment(String postId, String commentId, String userId){
        try {
            var response = this.postService.likeAndUnlikeCommentToPost(postId, commentId, userId);
            return response;
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
