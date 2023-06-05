package br.java.social_network.domain.services.user;

import br.java.social_network.application.models.user.IUserService;
import br.java.social_network.application.models.user.UserRequestDTO;
import br.java.social_network.domain.embedded.Profile;
import br.java.social_network.domain.entities.User;
import br.java.social_network.infrastructure.repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
@Qualifier("CreateUserServiceImpl")
public class CreateUserServiceImpl implements IUserService<UserRequestDTO, String> {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String execute(UserRequestDTO request) {
        var query = this.userRepository.findByEmail(request.email());

        if(!Objects.isNull(query)) throw new RuntimeException("Email already exists");

        var profile = Profile.create(request.profile());
        request.setProfile(profile);

        var passHash = this.passwordEncoder.encode(request.password());
        request.setPassword(passHash);

        var user = User.create(request);
        this.userRepository.insert(user);
        return user.getId().toString();
    }
}
