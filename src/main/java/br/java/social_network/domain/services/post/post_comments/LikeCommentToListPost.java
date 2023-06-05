package br.java.social_network.domain.services.post.post_comments;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.comment.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.embedded.Likes;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("LikeCommentToListPost")
public class LikeCommentToListPost implements IPostService<InputDataToCommentService, String> {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public String execute(InputDataToCommentService input) {
        var post = this.postRepository.findById(ConvertFormatId.toUUID(input.getPostId()));
        var like = Likes.builder().createLikeObj(ConvertFormatId.toUUID(input.getUserId()));
        var comment = post.get().getComments().stream()
                .filter(c -> c.getId().equals(ConvertFormatId.toUUID(input.getCommentId())))
                .findFirst();

        if (comment != null && comment.get().getLikes().contains(like)) {
            comment.get().getLikes().remove(like);
            this.postRepository.save(post.get());
            return "like removed!";
        } else {
            comment.get().getLikes().add(like);
            this.postRepository.save(post.get());
            return "like added!";
        }
    }
}
