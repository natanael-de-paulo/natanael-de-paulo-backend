package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.models.post.IPostService;
import io.github.natanaeldepaulo.api.application.models.post.PostRequest;
import io.github.natanaeldepaulo.api.application.models.post.PostDTO;
import io.github.natanaeldepaulo.api.application.models.post.UpdatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/posts/")
public class PostController {
    @Autowired
    private IPostService postService;

    @GetMapping
    public  ResponseEntity<List<PostDTO>> getPosts(@RequestParam String profileId){
        var response = postService.findPosts(profileId);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String postId){
        var response = postService.findPostById(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostRequest request, @RequestParam String profile_id){
        var response = postService.createPost(request, profile_id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody UpdatePostRequest request) throws Exception {
        postService.updatePost(postId, request);
        return ResponseEntity.ok().body("successfully updated");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) throws Exception {
        postService.deletePost(postId);
        return ResponseEntity.ok().body("successfully deleted");
    }

}
