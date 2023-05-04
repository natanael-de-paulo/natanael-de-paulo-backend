package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.ICommentService;
import io.github.natanaeldepaulo.api.application.specification.CommentRequest;
import io.github.natanaeldepaulo.api.application.specification.CommentResponse;

import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/post/{postId}/comment")
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/{commentId}")
    public ResponseEntity<CommentResponse> findById(@PathVariable String postId, @PathVariable String commentId){
        var response = commentService.findById(postId, commentId);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/create")
    public ResponseEntity<CommentResponse> createComment(@PathVariable String postId, @RequestParam String profileId, @RequestBody CommentRequest request){
        var response = commentService.create(request, postId, profileId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{commentId}")
    public  ResponseEntity updateComment(@PathVariable String postId, @PathVariable String commentId, @RequestBody CommentRequest dataToUpdate){
        var response = commentService.updateCommentToPost(postId, commentId, dataToUpdate);
        return null;
    }
}
