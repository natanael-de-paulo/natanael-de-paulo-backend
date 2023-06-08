package br.java.social_network.domain.user.services;

import br.java.social_network.infrastructure.exception.HandleNotFoundException;
import br.java.social_network.infrastructure.mappers.IMapper;
import br.java.social_network.application.user.services.IUserService;
import br.java.social_network.application.user.controllers.response.UserResponseDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.user.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Qualifier("FindUserByIdServiceImpl")
public class FindUserByIdServiceImpl implements IUserService<String, UserResponseDTO> {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("userMapper")
    private IMapper<User, UserResponseDTO> userMapper;

    @Override
    public UserResponseDTO execute(String userId) {
      try {
          var user = this.userRepository.findById(ConvertFormatId.toUUID(userId));
          return this.userMapper.toDTO(user.get());
      } catch (Exception e){
          if (e.getMessage() == "No value present") throw new HandleNotFoundException("User not found!");
          throw new HandleNotFoundException(e.getMessage());
      }
    }
}
