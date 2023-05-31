package br.java.social_network.application.models.post;

import br.java.social_network.domain.entities.Post;

public interface IPostMapper {
    Post toEntity(PostDTO postDTO);
    PostDTO toDTO(Post post);
}
