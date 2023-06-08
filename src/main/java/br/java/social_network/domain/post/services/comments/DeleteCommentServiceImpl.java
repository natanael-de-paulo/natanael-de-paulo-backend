package br.java.social_network.domain.post.services.comments;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.comment.services.ICommentService;
import br.java.social_network.application.comment.controllers.request.InputDataToCommentService;
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
            return "Deleted comments";
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
