package br.java.social_network.domain.post.services.comments;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.comment.controllers.response.CommentResponseDTO;
import br.java.social_network.application.post.comment.services.ICommentService;
import br.java.social_network.application.post.comment.controllers.request.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.post.embedded.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("CreateCommentServiceImpl")
public class CreateCommentServiceImpl implements ICommentService<InputDataToCommentService, CommentResponseDTO> {
    @Autowired
    @Qualifier("SaveCommentToPostListServiceImpl")
    private IPostService<Comment, Void> postService;

    @Override
    public CommentResponseDTO execute(InputDataToCommentService input){
        var comment = Comment.builder()
                .description(input.getCommentRequestDTO().description())
                .user_id(ConvertFormatId.toUUID(input.getUserId()))
                .postId(ConvertFormatId.toUUID(input.getPostId()));

        this.postService.execute(comment);
        return new CommentResponseDTO(comment);
    }
}
