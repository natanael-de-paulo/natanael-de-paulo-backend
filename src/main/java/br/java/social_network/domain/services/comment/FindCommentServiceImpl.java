package br.java.social_network.domain.services.comment;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostDTO;
import br.java.social_network.application.models.post.comment.CommentDTO;
import br.java.social_network.application.models.post.comment.ICommentService;
import br.java.social_network.application.models.post.comment.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.embedded.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindCommentServiceImpl")
public class FindCommentServiceImpl implements ICommentService<InputDataToCommentService, CommentDTO> {
    @Autowired
    @Qualifier("FindPostServiceImpl")
    private IPostService<String, PostDTO> postService;

    @Override
    public CommentDTO execute(InputDataToCommentService input){
        List<Comment> comments = this.postService.execute(input.getPostId()).getComments();
        var comment = comments.stream().filter(c -> c.getId().equals(ConvertFormatId.toUUID(input.getCommentId()))).findFirst();
        return new CommentDTO(comment.get());
    }
}
