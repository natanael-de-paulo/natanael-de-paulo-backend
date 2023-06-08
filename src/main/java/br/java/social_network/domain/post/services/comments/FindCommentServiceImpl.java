package br.java.social_network.domain.post.services.comments;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.application.comment.controllers.response.CommentResponseDTO;
import br.java.social_network.application.comment.services.ICommentService;
import br.java.social_network.application.comment.controllers.request.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.post.embedded.Comment;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Qualifier("FindCommentServiceImpl")
public class FindCommentServiceImpl implements ICommentService<InputDataToCommentService, CommentResponseDTO> {
    @Autowired
    @Qualifier("FindPostServiceImpl")
    private IPostService<String, PostResponseDTO> postService;

    @Override
    public CommentResponseDTO execute(InputDataToCommentService input){
        try{
            var comments = this.postService.execute(input.getPostId()).getComments();
            var comment = comments.stream().filter(c -> c.getId().equals(ConvertFormatId.toUUID(input.getCommentId()))).findFirst();
            return new CommentResponseDTO(comment.get());
        } catch (Exception e) {
            if (e.getMessage() == "No value present") throw new HandleNotFoundException("Comment not found");
            throw new HandleNotFoundException(e.getMessage());
        }
    }
}
