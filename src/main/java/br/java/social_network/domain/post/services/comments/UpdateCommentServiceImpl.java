package br.java.social_network.domain.post.services.comments;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.comment.services.ICommentService;
import br.java.social_network.application.post.comment.controllers.request.InputDataToCommentService;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("UpdateCommentServiceImpl")
public class UpdateCommentServiceImpl implements ICommentService<InputDataToCommentService, String> {
    @Autowired
    @Qualifier("UpdateCommentToListPostServiceImpl")
    private IPostService<InputDataToCommentService, Void> postService;

    @Override
    public String execute(InputDataToCommentService input){
        try {
            this.postService.execute(input);
            return "Updated comments";
        } catch (Exception e) {
            throw new HandleNotFoundException(e.getMessage());
        }
    }
}
