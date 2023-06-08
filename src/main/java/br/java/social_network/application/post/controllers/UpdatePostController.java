package br.java.social_network.application.post.controllers;

import br.java.social_network.application.post.services.IPostService;
import br.java.social_network.application.post.controllers.request.InputDataToUpdatePostService;
import br.java.social_network.application.post.controllers.request.UpdatePostRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class UpdatePostController {
    @Autowired
    @Qualifier("UpdatePostServiceImpl")
    private IPostService<InputDataToUpdatePostService, Void> postService;

    @PutMapping("/{postId}")
    public ResponseEntity<String> handle(@PathVariable String postId, @RequestBody UpdatePostRequestDTO request) throws Exception {
        var input = InputDataToUpdatePostService
                .build()
                .description(request.description())
                .title(request.title())
                .postId(postId);

        this.postService.execute(input);
        return ResponseEntity.ok().body("successfully updated");
    }
}
