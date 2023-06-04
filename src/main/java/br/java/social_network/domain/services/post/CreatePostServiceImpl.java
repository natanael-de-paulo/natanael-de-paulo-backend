package br.java.social_network.domain.services.post;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.infra_interfaces.IEventProvider;
import br.java.social_network.application.models.infra_interfaces.IUploadService;
import br.java.social_network.application.models.post.*;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostResponseDTO;
import br.java.social_network.domain.entities.Post;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Qualifier("CreatePostServiceImpl")
public class CreatePostServiceImpl implements IPostService<InputDataToPostService, PostResponseDTO> {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    private IEventProvider eventService;
    @Autowired
    private IUploadService uploadService;
    @Autowired
    @Qualifier("postMapper")
    private IMapper<Post, PostResponseDTO> postMapper;

    @Override
    public PostResponseDTO execute(InputDataToPostService input) {
        String imageUrl = null;
        var existFile = !Objects.isNull(input.getPostRequestDTO().file());

        if (existFile) imageUrl = this.uploadService.upload(input.getPostRequestDTO().file());

        var post = Post.builder()
                .title(input.getPostRequestDTO().title())
                .description(input.getPostRequestDTO().description())
                .image(existFile ? true : false)
                .imageUrl(existFile ? imageUrl : null)
                .userId(ConvertFormatId.toUUID(input.getUserId()));


        this.postRepository.insert(post);
        this.eventService.send("post-created", post.getId().toString());
        return this.postMapper.toDTO(post);
    }
}
