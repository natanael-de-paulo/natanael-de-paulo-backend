package br.java.social_network.domain.services.comment;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.comment.ICommentService;
import br.java.social_network.application.models.post.comment.InputDataToCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteCommentServiceImpl")
public class DeleteCommentServiceImpl  implements ICommentService<InputDataToCommentService, String>{
    @Autowired
    @Qualifier("DeleteCommentToListPostServiceImpl")
    private IPostService<InputDataToCommentService, Void> postService;

    @Override
    public String execute(InputDataToCommentService input){
        try {
            this.postService.execute(input);
            return "Deleted comment";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
