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

import java.util.ArrayList;
import java.util.List;

@Service
@Qualifier("ListPostServiceImpl")
public class ListPostServiceImpl implements IPostService<String, List<PostResponseDTO>> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    @Qualifier("postMapper")
    private IMapper<Post, PostResponseDTO>postMapper;

    @Override
    public List<PostResponseDTO> execute(String userId){
        var data = this.postRepository.findAll(ConvertFormatId.toUUID(userId));
        List<PostResponseDTO> postList = new ArrayList<>();

        for (Post post : data){
            var responseData = this.postMapper.toDTO(post);
            postList.add((PostResponseDTO) responseData);
        }

        return postList;
    }
}
