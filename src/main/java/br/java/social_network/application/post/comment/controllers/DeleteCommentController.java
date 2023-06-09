package br.java.social_network.application.post.comment.controllers;

import br.java.social_network.application.post.comment.services.ICommentService;
import br.java.social_network.application.post.comment.controllers.request.InputDataToCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments/{commentId}")
public class DeleteCommentController {
    @Autowired
    @Qualifier("DeleteCommentServiceImpl")
    private ICommentService<InputDataToCommentService, String> commentService;

    @DeleteMapping
    public ResponseEntity<String> handle(@PathVariable String postId, @PathVariable String commentId){
        var input = InputDataToCommentService.builder().postId(postId).commentId(commentId);
        var response = this.commentService.execute(input);
        return ResponseEntity.ok().body(response);
    }
}
