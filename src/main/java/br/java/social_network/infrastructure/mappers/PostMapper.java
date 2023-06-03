package br.java.social_network.infrastructure.mappers;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.post.PostDTO;
import br.java.social_network.domain.entities.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements IMapper<Post, PostDTO> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Post toEntity(PostDTO postDTO){
        return this.modelMapper.map(postDTO, Post.class);
    }
    @Override
    public PostDTO toDTO(Post post){
        return this.modelMapper.map(post, PostDTO.class);
    }
}
