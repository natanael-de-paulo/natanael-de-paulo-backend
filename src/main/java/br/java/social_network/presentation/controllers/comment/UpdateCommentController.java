package br.java.social_network.presentation.controllers.comment;

import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;
import br.java.social_network.application.models.post.comment.InputDataToCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class UpdateCommentController {
    @Autowired
    @Qualifier("UpdateCommentServiceImpl")
    private ICommentService<InputDataToCommentService, String> commentService;

    @PutMapping("/{commentId}")
    public ResponseEntity<String> handle(@PathVariable String postId, @PathVariable String commentId, @RequestBody CommentRequest dataToUpdate){
        var input = InputDataToCommentService.builder().postId(postId).commentId(commentId).commentRequest(dataToUpdate);
        var response = this.commentService.execute(input);
        return ResponseEntity.ok().body(response);
    }
}
