package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.IPostService;
import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/post/")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Optional<PostResponse>> findById(@PathVariable String id){
        var response = postService.findById(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<PostResponse>> createPost(@RequestBody PostRequest request, @RequestParam String profile_id){
        var response = postService.create(request, profile_id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody PostRequest request){
        postService.update(request, postId);
        return ResponseEntity.ok().body("successfully updated");
    }

}
