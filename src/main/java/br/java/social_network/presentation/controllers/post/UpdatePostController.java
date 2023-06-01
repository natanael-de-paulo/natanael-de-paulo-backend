package br.java.social_network.presentation.controllers.post;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.UpdatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class UpdatePostController {
    @Autowired
    @Qualifier("UpdatePostServiceImpl")
    private IPostService postService;

    @PutMapping("/{postId}")
    public ResponseEntity<String> handle(@PathVariable String postId, @RequestBody UpdatePostRequest request) throws Exception {
        this.postService.updatePost(postId, request);
        return ResponseEntity.ok().body("successfully updated");
    }
}
