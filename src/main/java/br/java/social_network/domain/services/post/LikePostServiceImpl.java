package br.java.social_network.domain.services.post;

import br.java.social_network.application.models.post.*;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.embedded.Likes;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("LikePostServiceImpl")
public class LikePostServiceImpl implements IPostService<InputDataToPostService, String> {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public String execute(InputDataToPostService input){
        var post = this.postRepository.findById(ConvertFormatId.toUUID(input.getPostId()));
        var like = new Likes(ConvertFormatId.toUUID(input.getUserId()));

        if (post.get().getLikes().contains(like)) {
            post.get().getLikes().remove(like);
            this.postRepository.save(post.get());
            return "like removed!";
        } else {
            post.get().getLikes().add(like);
            this.postRepository.save(post.get());
            return "like added!";
        }
    }
}
