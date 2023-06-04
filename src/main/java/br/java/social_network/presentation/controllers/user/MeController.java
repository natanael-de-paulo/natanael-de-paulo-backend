package br.java.social_network.presentation.controllers.user;

import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
        var response = this.userService.execute(userId);
        return ResponseEntity.ok().body(response);
    }
}
