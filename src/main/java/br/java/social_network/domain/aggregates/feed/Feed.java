package br.java.social_network.domain.aggregates.feed;

import br.java.social_network.application.post.controllers.request.PostRequestDTO;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.domain.post.Post;
import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Feed {
    private List<PostResponseDTO> postsToFeed;

    private Feed(){
        this.postsToFeed = new ArrayList<>();
    }

    public static Feed builder(){
        return new Feed();
    }
}
