package br.java.social_network.domain.services.post;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostDTO;
import br.java.social_network.application.models.post.PostRequest;
import br.java.social_network.application.models.post.UpdatePostRequest;
import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.embedded.Comment;
import br.java.social_network.infrastructure.repositories.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("FindPostServiceImpl")
public class FindPostServiceImpl implements IPostService {
    @Autowired
    private IPostRepository postRepository;
    @Autowired
    @Qualifier("postMapper")
    private IMapper postMapper;

    @Override
    public List<PostDTO> findPosts(String userId) {
        return null;
    }

    @Override
    public PostDTO findPostById(String postId){
        var post = this.postRepository.findById(ConvertFormatId.toUUID(postId));
        return (PostDTO) this.postMapper.toDTO(post.get());
    }

    @Override
    public PostDTO createPost(PostRequest post, String userId) {
        return null;
    }

    @Override
    public void updatePost(String postId, UpdatePostRequest dataToUpdate) throws Exception {

    }

    @Override
    public void deletePost(String postId) throws Exception {

    }

    @Override
    public String likePost(String postId, String userId) {
        return null;
    }

    @Override
    public String likeAndUnlikeCommentToPost(String postId, String commentId, String userId) {
        return null;
    }

    @Override
    public void saveCommentToList(Comment comment, String postId) {

    }

    @Override
    public void updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate) throws Exception {

    }

    @Override
    public void deleteCommentToPost(String postId, String commentId) throws Exception {

    }
}
