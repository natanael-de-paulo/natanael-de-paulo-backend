package br.java.social_network.domain.services.user;

import br.java.social_network.application.mapper.IMapper;
import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserDTO;
import br.java.social_network.application.models.user.UserRequest;
import br.java.social_network.application.utils.ConvertFormatId;
import br.java.social_network.domain.entities.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Qualifier("FindUserServiceImpl")
public class FindUserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    @Qualifier("userMapper")
    private IMapper userMapper;

    @Override
    public UserDTO findUserById(String userId) {
        var user = this.userRepository.findById(ConvertFormatId.toUUID(userId));
        return (UserDTO) this.userMapper.toDTO(user.get());
    }

    @Override
    public String create(UserRequest request) {
        return null;
    }

    @Override
    public User findUserByEmail(String email) {
        var user = this.userRepository.findByEmail(email);
        return user;
    }

    @Override
    public String upload(MultipartFile file) {
        return null;
    }

    @Override
    public String followUser(String userId) {
        return null;
    }
}
