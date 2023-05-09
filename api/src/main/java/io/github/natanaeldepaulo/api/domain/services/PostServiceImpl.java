package io.github.natanaeldepaulo.api.domain.services;

import io.github.natanaeldepaulo.api.application.models.post.IPostService;
import io.github.natanaeldepaulo.api.application.models.post.PostDTO;
import io.github.natanaeldepaulo.api.application.models.post.PostRequest;
import io.github.natanaeldepaulo.api.application.models.post.UpdatePostRequest;
import io.github.natanaeldepaulo.api.application.models.post.comment.CommentRequest;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.application.models.infraInterfaces.IUploadService;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.embedded.Likes;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import io.github.natanaeldepaulo.api.application.models.infraInterfaces.IEventProvider;
import io.github.natanaeldepaulo.api.infrastructure.repositories.IPostRepository;
import io.github.natanaeldepaulo.api.application.models.post.IPostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostRepository _postRepository;
    @Autowired
    private IEventProvider _eventService;
    @Autowired
    IUploadService _uploadService;
    @Autowired
    IPostMapper _postMapper;


    @Override
    public List<PostDTO> findPosts(String profileId){
        var data = _postRepository.findAll(ConvertFormatId.toUUID(profileId));
        List<PostDTO> postList = new ArrayList<>();

        for (Post post : data){
            var responseData = _postMapper.toDTO(post);
            postList.add(responseData);
        }

        return postList;
    }

    @Override
    public PostDTO findPostById(String postId){
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        return _postMapper.toDTO(post.get());
    }

    @Override
    public PostDTO createPost(PostRequest request, String profileId) {
        String imageUrl = null;
        if (request.getFile() != null) imageUrl = _uploadService.upload(request.getFile());

        var post = Post.create(
                request.getTitle(),
                request.getDescription(),
                request.getFile() != null ? true: null,
                request.getFile() != null ? imageUrl : null,
                ConvertFormatId.toUUID(profileId));

        _postRepository.insert(post);
        _eventService.send("post-created", post.getId().toString());
        return _postMapper.toDTO(post);
    }

    @Override
    public void updatePost(String postId, UpdatePostRequest dataToUpdate) throws Exception {
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        if (!post.isPresent()) throw new Exception("Post not found!");

        if(dataToUpdate.getTitle() != null) post.get().setTitle(dataToUpdate.getTitle().get());

        if(dataToUpdate.getDescription() != null) post.get().setDescription(dataToUpdate.getDescription().get());

        _postRepository.save(post.get());
    }

    @Override
    public void deletePost(String postId) throws Exception{
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        if (!post.isPresent()) throw new Exception("Post not found!");
        _postRepository.delete(post.get());
    }

    @Override
    public String likePost(String postId, String profileId){
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        var like = new Likes(ConvertFormatId.toUUID(profileId));

        if (post.get().getLikes().contains(like)) {
            post.get().getLikes().remove(like);
            _postRepository.save(post.get());
            return "like removed!";
        } else {
            post.get().getLikes().add(like);
            _postRepository.save(post.get());
            return "like added!";
        }
    }

    @Override
    public void saveCommentToList(Comment comment, String postId) {
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        post.get().getComments().add(comment);
        _postRepository.save(post.get());
    }

    @Override
    public void updateCommentToPost(String postId, String commentId, CommentRequest dataToUpdate) throws Exception {
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        if (!post.isPresent()) throw new Exception("Post not found!");

        var comment = post.get().getComments().stream()
                .filter(c -> c.getId().equals(ConvertFormatId.toUUID(commentId)))
                .findFirst();

        if (!comment.isPresent()) throw new Exception("Comment not found!");
        comment.get().setDescription(dataToUpdate.description);
        _postRepository.save(post.get());
    }

    @Override
    public void deleteCommentToPost(String postId, String commentId) throws Exception {
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        if (!post.isPresent()) throw new Exception("Post not found!");

        var comment = post.get().getComments().stream()
                .filter(c -> c.getId().equals(ConvertFormatId.toUUID(commentId)))
                .findFirst();

        if (!comment.isPresent()) throw new Exception("Comment not found!");

        post.get().getComments().remove(comment.get());
        _postRepository.save(post.get());
    }
}
