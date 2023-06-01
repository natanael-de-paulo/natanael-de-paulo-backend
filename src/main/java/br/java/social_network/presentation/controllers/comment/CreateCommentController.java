package br.java.social_network.presentation.controllers.comment;

import br.java.social_network.application.models.post.comment.CommentDTO;
import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CreateCommentController {
    @Autowired
    @Qualifier("CreateCommentServiceImpl")
    private ICommentService commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> handle(@PathVariable String postId, @RequestParam String userId, @RequestBody CommentRequest request){
        var response = this.commentService.create(postId, userId, request);
        return ResponseEntity.ok(response);
    }
}
