package br.java.social_network.infrastructure.mappers;

import br.java.social_network.application.models.post.IPostMapper;
import br.java.social_network.application.models.post.PostDTO;
import br.java.social_network.domain.entities.Post;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PostMapper implements IPostMapper {
    @Autowired
    private ModelMapper _modelMapper;

    @Override
    public Post toEntity(PostDTO postDTO){
        return _modelMapper.map(postDTO, Post.class);
    }
    @Override
    public PostDTO toDTO(Post post){
        return _modelMapper.map(post, PostDTO.class);
    }
}
