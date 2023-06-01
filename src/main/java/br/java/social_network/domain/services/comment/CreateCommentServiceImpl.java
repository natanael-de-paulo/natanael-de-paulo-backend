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

@Service
@Qualifier("CreateCommentServiceImpl")
public class CreateCommentServiceImpl implements ICommentService {
    @Autowired
    @Qualifier("SaveCommentToPostListServiceImpl")
    private IPostService postService;

    @Override
    public CommentDTO create(String postId, String userId, CommentRequest request){
        var comment = Comment.create(
                request.description,
                ConvertFormatId.toUUID(postId) ,
                ConvertFormatId.toUUID(userId)
        );

        this.postService.saveCommentToList(comment, comment.getPost_id().toString());
        return new CommentDTO(comment);
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
    public String likeAndUnlikeComment(String postId, String commentId, String userId) {
        return null;
    }
}
