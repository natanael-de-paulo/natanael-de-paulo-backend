package br.java.social_network.application.feed.controllers;

import br.java.social_network.application.feed.IFeedService;
import br.java.social_network.application.post.controllers.response.PostResponseDTO;
import br.java.social_network.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
public class FeedController {
    @Autowired
    private IFeedService feedService;

    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> handle(){
        var user = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        System.out.print(user.getId());
        var output = this.feedService.execute(user.getId().toString());
        return ResponseEntity.status(HttpStatus.OK).body(output);
    }
}
