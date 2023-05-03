package io.github.natanaeldepaulo.api.application.implementation;

import io.github.natanaeldepaulo.api.application.ICommentService;
import io.github.natanaeldepaulo.api.application.IPostService;
import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.CommentResponse;
import io.github.natanaeldepaulo.api.application.utils.ConvertFormatId;
import io.github.natanaeldepaulo.api.domain.embedded.Comment;
import io.github.natanaeldepaulo.api.domain.interfaces.IPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private IPostService postService;

    @Override
    public CommentResponse findById(String postId, String commentId){
        var _commentId = ConvertFormatId.toUUID(commentId);
        List<Comment> comments = postService.findById(postId).get().getComments();
        var comment = comments.stream().filter(c -> c.getId().equals(_commentId)).findFirst();
        return new CommentResponse(comment.get());
    }

    @Override
    public Optional<CommentResponse> create(CommentRequest request, String postId, String profileId){
        var comment = Comment.create(
                request.description,
                ConvertFormatId.toUUID(postId) ,
                ConvertFormatId.toUUID(profileId)
        );

        postService.saveCommentToList(comment, comment.getPost_id().toString());
        return new CommentResponse(comment);
    }
}
