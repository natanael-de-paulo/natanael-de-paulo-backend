package br.java.social_network.application.feed;

import br.java.social_network.application.post.controllers.response.PostResponseDTO;

import java.util.List;

public interface IFeedService {
   List<PostResponseDTO> execute(String userId);
}
