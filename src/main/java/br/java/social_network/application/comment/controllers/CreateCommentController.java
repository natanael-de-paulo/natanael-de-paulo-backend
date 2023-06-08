package br.java.social_network.application.comment.controllers;

import br.java.social_network.application.comment.controllers.response.CommentResponseDTO;
import br.java.social_network.application.comment.controllers.request.CommentRequestDTO;
import br.java.social_network.application.comment.services.ICommentService;
import br.java.social_network.application.comment.controllers.request.InputDataToCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CreateCommentController {
    @Autowired
    @Qualifier("CreateCommentServiceImpl")
    private ICommentService<InputDataToCommentService, CommentResponseDTO> commentService;

    @PostMapping("/create")
    public ResponseEntity<CommentResponseDTO> handle(@PathVariable String postId, @RequestParam String userId, @RequestBody CommentRequestDTO request){
        var input = InputDataToCommentService
                .builder()
                .postId(postId)
                .userId(userId)
                .commentRequest(request);

        var response = this.commentService.execute(input);
        return ResponseEntity.ok(response);
    }
}
