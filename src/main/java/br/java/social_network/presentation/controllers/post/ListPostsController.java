package br.java.social_network.presentation.controllers.post;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.PostDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class ListPostsController {
    @Autowired
    @Qualifier("ListPostServiceImpl")
    private IPostService<String, List<PostDTO>> postService;

    @GetMapping
    public ResponseEntity<List<PostDTO>> handle(@RequestParam String userId){
        var response = this.postService.execute(userId);
        return ResponseEntity.ok().body(response);
    }
}
