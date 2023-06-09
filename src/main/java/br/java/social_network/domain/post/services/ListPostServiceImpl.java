package br.java.social_network.domain.post.services;

import br.java.social_network.infrastructure.mappers.IMapper;
import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.post.Post;
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
    private IMapper<Post, PostResponseDTO> postMapper;

    @Override
    public List<PostResponseDTO> execute(String userId){
        var data = this.postRepository.findAll(ConvertFormatId.toUUID(userId));
        List<PostResponseDTO> postList = new ArrayList<>();

        for (Post post : data){
            var responseData = this.postMapper.toDTO(post);
            postList.add(responseData);
        }

        return postList;
    }
}
