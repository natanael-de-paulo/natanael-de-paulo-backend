package br.java.social_network.domain.services.user;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserResponseDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.entities.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

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
        var user = this.userRepository.findById(ConvertFormatId.toUUID(userId));
        return this.userMapper.toDTO(user.get());
    }
}
