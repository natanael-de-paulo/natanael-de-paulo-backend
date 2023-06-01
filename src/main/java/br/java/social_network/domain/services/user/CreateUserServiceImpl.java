package br.java.social_network.domain.services.user;

import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserDTO;
import br.java.social_network.application.models.user.UserRequest;
import br.java.social_network.domain.embedded.Profile;
import br.java.social_network.domain.entities.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;

@Service
@Qualifier("CreateUserServiceImpl")
public class CreateUserServiceImpl implements IUserService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO findUserById(String userId) {
        return null;
    }

    @Override
    public String create(UserRequest request) {
        var query = this.userRepository.findByEmail(request.getEmail());

        if(!Objects.isNull(query)) throw new RuntimeException("Email already exists");

        var profile = Profile.create(request.getProfile());
        request.setProfile(profile);

        var passHash = this.passwordEncoder.encode(request.getPassword());
        request.setPassword(passHash);

        var user = User.create(request);
        this.userRepository.insert(user);
        return user.getId().toString();
    }

    @Override
    public User findUserByEmail(String email) {
        return null;
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
