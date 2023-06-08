package br.java.social_network.application.post.controllers.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputDataToPostService {
    private PostRequestDTO postRequestDTO;
    private String userId;
    private String postId;
    private MultipartFile file;

    public static InputDataToPostService build(){
        return new InputDataToPostService();
    }

    public InputDataToPostService postRequest(PostRequestDTO postRequestDTO){
        this.postRequestDTO = postRequestDTO;
        return this;
    }

    public InputDataToPostService userId(String userId){
        this.userId = userId;
        return this;
    }

    public InputDataToPostService postId(String postId){
        this.postId = postId;
        return this;
    }

    public InputDataToPostService file( MultipartFile file){
        this.file = file;
        return this;
    }
}
