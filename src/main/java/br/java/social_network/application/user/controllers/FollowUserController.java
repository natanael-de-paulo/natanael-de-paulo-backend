package br.java.social_network.application.user.controllers;

import br.java.social_network.application.user.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/follow")
public class FollowUserController {
    @Autowired
    @Qualifier("FollowUserServiceImpl")
    private IUserService<String, String> userService;
    @PostMapping
    public ResponseEntity<String> handle(@RequestParam String userId){
        var response = this.userService.execute(userId);
        return ResponseEntity.ok(response);
    }
}
