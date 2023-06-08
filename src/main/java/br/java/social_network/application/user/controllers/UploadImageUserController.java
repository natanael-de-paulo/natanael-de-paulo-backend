package br.java.social_network.application.user.controllers;

import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/upload")
public class UploadImageUserController {
    @Autowired
    @Qualifier("UploadFileServiceImpl")
    private IUserService<MultipartFile, String> userService;
    @PostMapping
    public ResponseEntity<String> handle(@RequestParam(required = false) MultipartFile file){
        String response = null;
        try {
            response = this.userService.execute(file);
            return ResponseEntity.ok(response);
        } catch (HandleNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
