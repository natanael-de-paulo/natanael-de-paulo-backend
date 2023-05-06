package io.github.natanaeldepaulo.api.infrastructure.mappers;

import io.github.natanaeldepaulo.api.application.models.post.PostDTO;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class PostMapper implements IPostMapper{
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
