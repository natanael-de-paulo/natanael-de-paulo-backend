package br.java.social_network.application.models.post;

import br.java.social_network.domain.entities.Post;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputDataToUpdatePostService {
    private String title;
    private String description;
    private String userId;
    private String postId;

    public static InputDataToUpdatePostService build(){
        return new InputDataToUpdatePostService();
    }

    public InputDataToUpdatePostService title(String title){
        this.title = title;
        return this;
    }

    public InputDataToUpdatePostService description(String description){
        this.description = description;
        return this;
    }

    public InputDataToUpdatePostService userId(String userId){
        this.userId = userId;
        return this;
    }

    public InputDataToUpdatePostService postId(String postId){
        this.postId = postId;
        return this;
    }
}
