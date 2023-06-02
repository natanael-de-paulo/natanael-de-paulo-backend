package br.java.social_network.presentation.controllers.comment;

import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class UpdateCommentController {
    @Autowired
    @Qualifier("UpdateCommentServiceImpl")
    private ICommentService commentService;

    @PutMapping("/{commentId}")
    public ResponseEntity<String> handle(@PathVariable String postId, @PathVariable String commentId, @RequestBody CommentRequest dataToUpdate){
        var response = this.commentService.updateCommentToPost(postId, commentId, dataToUpdate);
        return ResponseEntity.ok().body(response);
    }
}
