package br.java.social_network.domain.post.services;

import br.java.social_network.application.post.controllers.request.InputDataToUpdatePostService;
import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Qualifier("UpdatePostServiceImpl")
public class UpdatePostServiceImpl implements IPostService<InputDataToUpdatePostService, Void> {
    @Autowired
    private IPostRepository postRepository;

    @Override
    public Void execute(InputDataToUpdatePostService input) {
      try{
          var post = this.postRepository.findById(ConvertFormatId.toUUID(input.getPostId()));
          if (!post.isPresent()) throw new Exception("Post not found!");

          if(!Objects.isNull(input.getTitle())) post.get().title(input.getTitle());

          if(!Objects.isNull(input.getDescription())) post.get().description(input.getDescription());

          this.postRepository.save(post.get());
          return null;
      } catch (Exception e){
          throw new HandleNotFoundException(e.getMessage());
      }
    }
}
