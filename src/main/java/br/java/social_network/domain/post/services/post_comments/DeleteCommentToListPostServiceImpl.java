package br.java.social_network.domain.post.services.post_comments;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.comment.controllers.request.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeleteCommentToListPostServiceImpl")
public class DeleteCommentToListPostServiceImpl implements IPostService<InputDataToCommentService, Void>{
    @Autowired
    private IPostRepository postRepository;

    @Override
    public Void execute(InputDataToCommentService input) {
        var post = this.postRepository.findById(ConvertFormatId.toUUID(input.getPostId()));
        if (!post.isPresent()) throw new HandleNotFoundException("Post not found!");

        var comment = post.get().getComments().stream()
                .filter(c -> c.getId().equals(ConvertFormatId.toUUID(input.getCommentId())))
                .findFirst();

        if (!comment.isPresent()) throw new HandleNotFoundException("Comment not found!");

        post.get().getComments().remove(comment.get());
        this.postRepository.save(post.get());
        return null;
    }
}
