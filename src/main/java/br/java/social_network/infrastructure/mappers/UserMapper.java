package br.java.social_network.infrastructure.mappers;

import br.java.social_network.application.models.user.IUserMapper;
import br.java.social_network.application.models.user.UserDTO;
import br.java.social_network.domain.entities.User;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class UserMapper implements IUserMapper {
    @Autowired
    private ModelMapper _modelMapper;

    public User toEntity(UserDTO userDTO){
        return _modelMapper.map(userDTO, User.class);
    }
    public UserDTO toDTO(User user){
        return _modelMapper.map(user, UserDTO.class);
    }
}
