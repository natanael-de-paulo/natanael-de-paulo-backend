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

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/posts/")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public  ResponseEntity<List<PostResponse>> getPosts(@RequestParam String profileId){
        var response = postService.findPosts(profileId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<Optional<PostResponse>> getPostById(@PathVariable String postId){
        var response = postService.findPostById(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<Optional<PostResponse>> createPost(@RequestBody PostRequest request, @RequestParam String profile_id){
        var response = postService.createPost(request, profile_id);
        return ResponseEntity.ok(response);
    }

//    @PutMapping("/{postId}")
//    public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody PostRequest request){
//        postService.update(request, postId);
//        return ResponseEntity.ok().body("successfully updated");
//    }

}
