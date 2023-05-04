package io.github.natanaeldepaulo.api.application.implementation;

import io.github.natanaeldepaulo.api.application.IPostService;
import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import io.github.natanaeldepaulo.api.application.specification.UpdatePostRequest;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import io.github.natanaeldepaulo.api.domain.interfaces.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PostServiceImpl implements IPostService {
    @Autowired
    IPostRepository _postRepository;


    @Override
    public List<PostResponse> findPosts(String profileId){
        var data = _postRepository.findAll(ConvertFormatId.toUUID(profileId));
        List<PostResponse> postList = new ArrayList<>();

        for (Post post : data){
            var responseData = new PostResponse(post);
            postList.add(responseData);
        }

        return postList;
    }

    @Override
    public Optional<PostResponse> findPostById(String postId){
        var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
        var response = new PostResponse(post.get());
        return Optional.of(response);
    }

    @Override
    public Optional<PostResponse> createPost(PostRequest request, String profile_id){
        var profileId = UUID.fromString(profile_id);
        var post = Post.create(request, profileId);
        _postRepository.insert(post);
        var response = new PostResponse(post);
        return Optional.of(response);
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


//    @Override
//    public void update(PostRequest dataToUpdate, String postId){
//       var post = _postRepository.findById(ConvertFormatId.toUUID(postId));
//       post.get().setTitle(dataToUpdate.getTitle());
//       post.get().setTitle(dataToUpdate.getDescription());
//
//       _postRepository.save(post.get());
//    }
}
