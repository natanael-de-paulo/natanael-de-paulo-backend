package br.java.social_network.application.post.controllers;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.controllers.request.InputDataToPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class LikePostController {
    @Autowired
    @Qualifier("LikePostServiceImpl")
    private IPostService<InputDataToPostService, String> postService;

    @PostMapping("/{postId}/like")
    public ResponseEntity<String> handle(@PathVariable String postId, @RequestParam String userId){
        var input = InputDataToPostService.build().postId(postId).userId(userId);
        var response = this.postService.execute(input);
        return ResponseEntity.ok(response);
    }
}
