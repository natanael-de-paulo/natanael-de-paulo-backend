package br.java.social_network.presentation.controllers;

import br.java.social_network.application.models.post.comment.CommentDTO;
import br.java.social_network.application.models.post.comment.CommentRequest;
import br.java.social_network.application.models.post.comment.ICommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/posts/comments")
public class CommentController {
    @Autowired
    private ICommentService _commentService;

    @GetMapping
    public ResponseEntity<CommentDTO> findById(@RequestParam String postId, @RequestParam String commentId){
        var response = _commentService.findById(postId, commentId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentDTO> createComment(@RequestParam String postId, @RequestParam String userId, @RequestBody CommentRequest request){
        var response = _commentService.create(postId, userId, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update")
    public  ResponseEntity<String> updateComment(@RequestParam String postId, @RequestParam String commentId, @RequestBody CommentRequest dataToUpdate){
        var response = _commentService.updateCommentToPost(postId, commentId, dataToUpdate);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete")
    public  ResponseEntity<String> deleteComment(@RequestParam String postId, @RequestParam String commentId){
        var response = _commentService.deleteCommentToPost(postId, commentId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/like")
    public ResponseEntity<String> likeComment(@RequestParam String postId, @RequestParam String commentId, @RequestParam String userId){
        var response = _commentService.likeAndUnlikeComment(postId, commentId, userId);
        return ResponseEntity.ok(response);
    }

}
