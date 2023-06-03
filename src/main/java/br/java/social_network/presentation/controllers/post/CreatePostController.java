package br.java.social_network.presentation.controllers.post;

import br.java.social_network.application.models.post.IPostService;
import br.java.social_network.application.models.post.InputDataToPostService;
import br.java.social_network.application.models.post.PostDTO;
import br.java.social_network.application.models.post.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@RestController
@RequestMapping("/posts/create")
public class CreatePostController {
    @Autowired
    @Qualifier("CreatePostServiceImpl")
    private IPostService<InputDataToPostService, PostDTO> postService;

    @PostMapping
    public ResponseEntity<PostDTO> handle(PostRequest postRequest, @RequestParam String userId, @RequestPart(required = false) MultipartFile file) {
        if (!Objects.isNull(file)) postRequest.setFile(file);
        var input = InputDataToPostService.build().postRequest(postRequest).userId(userId);
        var response = this.postService.execute(input);
        return ResponseEntity.ok(response);
    }
}
