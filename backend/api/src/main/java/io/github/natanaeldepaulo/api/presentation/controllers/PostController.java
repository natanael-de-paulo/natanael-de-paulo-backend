package io.github.natanaeldepaulo.api.presentation.controllers;

import io.github.natanaeldepaulo.api.application.IPostService;
import io.github.natanaeldepaulo.api.application.specification.PostRequest;
import io.github.natanaeldepaulo.api.application.specification.PostResponse;
import io.github.natanaeldepaulo.api.domain.entities.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/v1/post")
public class PostController {
    @Autowired
    private IPostService postService;
    @PostMapping("/create")
    public ResponseEntity<Optional<PostResponse>> createPost(@RequestBody PostRequest request, @RequestParam String profile_id){
        var response = postService.create(request, profile_id);
        return ResponseEntity.ok(response);
    }

}
