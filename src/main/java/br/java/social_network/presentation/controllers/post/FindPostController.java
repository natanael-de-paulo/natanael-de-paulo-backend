package br.java.social_network.presentation.controllers.post;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class FindPostController {
    @Autowired
    @Qualifier("FindPostServiceImpl")
    private IPostService<String, PostResponseDTO> postService;

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> handle(@PathVariable String postId){
        var response = this.postService.execute(postId);
        return ResponseEntity.ok(response);
    }
}
