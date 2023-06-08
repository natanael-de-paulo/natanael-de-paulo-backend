package br.java.social_network.application.post.controllers;

import br.java.social_network.application.post.services.IPostService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class DeletePostController {
    @Autowired
    @Qualifier("DeletePostServiceImpl")
    private IPostService<String, Void> postService;

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> handle(@PathVariable String postId) {
        this.postService.execute(postId);
        return ResponseEntity.ok().body("successfully deleted");
    }
}
