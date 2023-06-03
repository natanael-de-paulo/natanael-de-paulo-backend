package br.java.social_network.domain.services.post;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("DeletePostServiceImpl")
public class DeletePostServiceImpl implements IPostService<String, Void> {
    @Autowired
    private IPostRepository postRepository;

    public Void execute(String postId) {
        var post = this.postRepository.findById(ConvertFormatId.toUUID(postId));
        if (!post.isPresent()) throw new RuntimeException("Post not found!");
        this.postRepository.delete(post.get());
        return null;
    }
}
