package br.java.social_network.domain.post.services.post_comments;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.comment.controllers.request.InputDataToCommentService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.post.embedded.Likes;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Qualifier("LikeCommentToListPost")
public class LikeCommentToListPost implements IPostService<InputDataToCommentService, String> {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public String execute(InputDataToCommentService input) {
        var post = this.postRepository.findById(ConvertFormatId.toUUID(input.getPostId()));
        if (!post.isPresent()) throw new HandleNotFoundException("Post not found!");

        var like = Likes.builder().createLikeObj(ConvertFormatId.toUUID(input.getUserId()));
        var comment = post.get().getComments().stream()
                .filter(c -> c.getId().equals(ConvertFormatId.toUUID(input.getCommentId())))
                .findFirst();

        if (!comment.isPresent() && !Objects.isNull(comment)) throw new HandleNotFoundException("Comment not found!");

        if (!Objects.isNull(comment) && comment.get().getLikes().contains(like)) {
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
