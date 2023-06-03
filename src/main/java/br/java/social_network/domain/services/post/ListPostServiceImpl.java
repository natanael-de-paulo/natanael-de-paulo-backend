package br.java.social_network.domain.services.post;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostDTO;
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
public class ListPostServiceImpl implements IPostService<String, List<PostDTO>> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    @Qualifier("postMapper")
    private IMapper<Post, PostDTO>postMapper;

    @Override
    public List<PostDTO> execute(String userId){
        var data = this.postRepository.findAll(ConvertFormatId.toUUID(userId));
        List<PostDTO> postList = new ArrayList<>();

        for (Post post : data){
            var responseData = this.postMapper.toDTO(post);
            postList.add((PostDTO) responseData);
        }

        return postList;
    }
}
