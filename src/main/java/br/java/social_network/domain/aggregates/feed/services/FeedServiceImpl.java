package br.java.social_network.domain.aggregates.feed.services;

import br.java.social_network.application.feed.IFeedService;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.application.user.controllers.response.UserResponseDTO;
import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.domain.aggregates.feed.Feed;
import br.java.social_network.domain.post.Post;
import br.java.social_network.infrastructure.mappers.IMapper;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedServiceImpl implements IFeedService {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    @Qualifier("postMapper")
    private IMapper<Post, PostResponseDTO> postMapper;
    @Autowired
    @Qualifier("FindUserByIdServiceImpl")
    private IUserService<String, UserResponseDTO> userService;

    public List<PostResponseDTO> execute(String userId){
        try {
            var user = this.userService.execute(userId);
            var listPostsToFeed = this.postRepository.findPostsByFollowingProfiles(user.getProfile().getFollowing());
            var feed = Feed.builder();

            for ( Post post : listPostsToFeed) {
                var postDTO = this.postMapper.toDTO(post);
                feed.getPostsToFeed().add(postDTO);
            }

            return feed.getPostsToFeed();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
