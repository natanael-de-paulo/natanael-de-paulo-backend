package io.github.natanaeldepaulo.api.application.services;

import lombok.Data;
import java.util.UUID;

@Data
public class UserResponse {
    public UUID _id;
    public String name;
    public String email;

    public UserResponse(UUID id, String _name, String _email){
        this._id = id;
        this.name = _name;
        this.email = _email;
    }
}
