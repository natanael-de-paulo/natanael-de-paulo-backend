package br.java.social_network.domain.services.post;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostResponseDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.entities.Post;
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
        var post = this.postRepository.findById(ConvertFormatId.toUUID(postId));
        return this.postMapper.toDTO(post.get());
    }
}
