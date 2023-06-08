package br.java.social_network.domain.post.services;

import br.java.social_network.infrastructure.mappers.IMapper;
import br.java.social_network.infrastructure.providers.interfaces.IEventProvider;
import br.java.social_network.infrastructure.providers.interfaces.IUploadService;
import br.java.social_network.application.post.controllers.request.InputDataToPostService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.domain.post.Post;
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
        var existFile = !Objects.isNull(input.getFile());

        if (existFile) imageUrl = this.uploadService.upload(input.getFile());

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
