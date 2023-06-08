package br.java.social_network.domain.post.services;

import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.mappers.IMapper;
import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.post.Post;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FindPostServiceImpl")
public class FindPostServiceImpl implements IPostService<String, PostResponseDTO> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    @Qualifier("postMapper")
    private IMapper<Post, PostResponseDTO> postMapper;

    @Override
    public PostResponseDTO execute(String postId){
        try {
            var post = this.postRepository.findById(ConvertFormatId.toUUID(postId));
            return this.postMapper.toDTO(post.get());
        } catch (Exception e) {
            if (e.getMessage() == "No value present") throw new HandleNotFoundException("Post not found!");
            throw new HandleNotFoundException(e.getMessage());
        }
    }
}
