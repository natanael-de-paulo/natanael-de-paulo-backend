package br.java.social_network.application.models.post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputDataToPostService {
    private PostRequestDTO postRequestDTO;
    private String userId;
    private String postId;

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
}
