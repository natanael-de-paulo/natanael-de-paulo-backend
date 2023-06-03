package br.java.social_network.domain.services.post.post_comments;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.comment.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
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
        if (!post.isPresent()) throw new RuntimeException("Post not found!");

        var comment = post.get().getComments().stream()
                .filter(c -> c.getId().equals(ConvertFormatId.toUUID(input.getCommentId())))
                .findFirst();

        if (!comment.isPresent()) throw new RuntimeException("Comment not found!");

        post.get().getComments().remove(comment.get());
        this.postRepository.save(post.get());
        return null;
    }
}
