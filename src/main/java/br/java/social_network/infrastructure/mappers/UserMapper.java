package br.java.social_network.infrastructure.mappers;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.user.UserResponseDTO;
import br.java.social_network.domain.entities.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements IMapper<User, UserResponseDTO> {
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public User toEntity(UserResponseDTO userDTO){
        return this.modelMapper.map(userDTO, User.class);
    }
    @Override
    public UserResponseDTO toDTO(User user){
        return this.modelMapper.map(user, UserResponseDTO.class);
    }
}
