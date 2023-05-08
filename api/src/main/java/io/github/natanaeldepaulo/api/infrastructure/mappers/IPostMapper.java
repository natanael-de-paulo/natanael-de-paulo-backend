package io.github.natanaeldepaulo.api.infrastructure.mappers;

import io.github.natanaeldepaulo.api.application.models.post.PostDTO;
import io.github.natanaeldepaulo.api.domain.entities.Post;

public interface IPostMapper {
    Post toEntity(PostDTO postDTO);
    PostDTO toDTO(Post post);
}
