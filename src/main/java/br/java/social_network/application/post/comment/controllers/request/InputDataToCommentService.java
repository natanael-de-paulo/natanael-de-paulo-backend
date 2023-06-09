package br.java.social_network.application.post.comment.controllers.request;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputDataToCommentService {
    private String postId;
    private String commentId;
    private String userId;
    private CommentRequestDTO commentRequestDTO;

    public static InputDataToCommentService builder(){
        return new InputDataToCommentService();
    }

    public InputDataToCommentService commentId(String commentId){
        this.commentId = commentId;
        return this;
    }

    public InputDataToCommentService userId(String userId){
        this.userId = userId;
        return this;
    }

    public InputDataToCommentService postId(String postId){
        this.postId = postId;
        return this;
    }

    public InputDataToCommentService commentRequest(CommentRequestDTO commentRequestDTO){
        this.commentRequestDTO = commentRequestDTO;
        return this;
    }
}
