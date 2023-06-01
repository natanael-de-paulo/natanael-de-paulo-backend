package br.java.social_network.domain.services.comment;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.comment.CommentDTO;
import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.embedded.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindCommentServiceImpl")
public class FindCommentServiceImpl implements ICommentService {
    @Autowired
    @Qualifier("FindPostServiceImpl")
    private IPostService postService;

    @Override
    public CommentDTO create(String postId, String userId, CommentRequest request) {
        return null;
    }

    @Override
    public CommentDTO findById(String postId, String commentId){
        List<Comment> comments = this.postService.findPostById(postId).getComments();
        var comment = comments.stream().filter(c -> c.getId().equals(ConvertFormatId.toUUID(commentId))).findFirst();
        return new CommentDTO(comment.get());
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
    public String likeAndUnlikeComment(String postId, String commentId, String userId) {
        return null;
    }
}
