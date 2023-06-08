package br.java.social_network.application.user.controllers;

import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.application.user.controllers.response.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users/me")
public class MeController {
    @Autowired
    @Qualifier("FindUserByIdServiceImpl")
    private IUserService<String, UserResponseDTO> userService;
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> handle(@PathVariable String userId){
        HttpHeaders headers = new HttpHeaders();
        var response = this.userService.execute(userId);
        return ResponseEntity.ok().body(response);
    }
}
