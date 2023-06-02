package br.java.social_network.domain.services.user;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserDTO;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@Qualifier("FindUserByIdServiceImpl")
public class FindUserByIdServiceImpl implements IUserService<String, UserDTO> {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    @Qualifier("userMapper")
    private IMapper userMapper;

    @Override
    public UserDTO execute(String userId) {
        var user = this.userRepository.findById(ConvertFormatId.toUUID(userId));
        return (UserDTO) this.userMapper.toDTO(user.get());
    }
}
