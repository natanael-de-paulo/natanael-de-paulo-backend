package br.java.social_network.infrastructure.mappers;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.post.PostResponseDTO;
import br.java.social_network.domain.entities.Post;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostMapper implements IMapper<Post, PostResponseDTO> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public Post toEntity(PostResponseDTO postResponseDTO){
        return this.modelMapper.map(postResponseDTO, Post.class);
    }
    @Override
    public PostResponseDTO toDTO(Post post){
        return this.modelMapper.map(post, PostResponseDTO.class);
    }
}
