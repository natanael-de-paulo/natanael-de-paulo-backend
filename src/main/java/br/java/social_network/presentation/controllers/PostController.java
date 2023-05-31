package br.java.social_network.presentation.controllers;

import br.java.social_network.application.models.post.UpdatePostRequest;
import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostRequest;
import br.java.social_network.application.models.post.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/posts")
public class PostController {
    @Autowired
    private IPostService _postService;

    @GetMapping
    public  ResponseEntity<List<PostDTO>> getPosts(@RequestParam String userId){
        var response = _postService.findPosts(userId);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable String postId){
        var response = _postService.findPostById(postId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/create")
    public ResponseEntity<PostDTO> createPost(PostRequest postRequest, @RequestParam String userId, @RequestPart(required = false) MultipartFile file) {
        if (file != null) {
            postRequest.setFile(file);
        }
        var response = _postService.createPost(postRequest, userId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(@PathVariable String postId, @RequestBody UpdatePostRequest request) throws Exception {
        _postService.updatePost(postId, request);
        return ResponseEntity.ok().body("successfully updated");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable String postId) throws Exception {
        _postService.deletePost(postId);
        return ResponseEntity.ok().body("successfully deleted");
    }

    @PostMapping("/like")
    public ResponseEntity<String> likePost(@RequestParam String postId, @RequestParam String userId){
        var response = _postService.likePost(postId, userId);
        return ResponseEntity.ok(response);
    }

}
