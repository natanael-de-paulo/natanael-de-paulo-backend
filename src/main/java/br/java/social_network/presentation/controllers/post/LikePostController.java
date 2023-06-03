package br.java.social_network.presentation.controllers.post;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.InputDataToPostService;
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
