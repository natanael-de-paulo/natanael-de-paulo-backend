package br.java.social_network.presentation.controllers.comment;

import br.java.social_network.application.models.post.comment.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class LikeCommentController {
    @Autowired
    @Qualifier("LikeCommentServiceImpl")
    private ICommentService commentService;

    @PostMapping("/{commentId}/like")
    public ResponseEntity<String> handle(@PathVariable String postId, @PathVariable String commentId, @RequestParam String userId){
        var response = this.commentService.likeAndUnlikeComment(postId, commentId, userId);
        return ResponseEntity.ok(response);
    }
}
